package ru.job4j.pack;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.ParseException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static java.lang.Thread.currentThread;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class Args {
    private CommandLine cmd;
    private String iniDirectory;
    private List<String> excludeExtList = new ArrayList<>();
    private final ConcurrentLinkedQueue<File> fileList = new ConcurrentLinkedQueue<>();
    private final List<File> filteredFileList = new ArrayList<>();
    private boolean endOfList = false;

    public void directory() {
        Queue<File> queueFiles = new LinkedList<>();
        iniDirectory = cmd.getOptionValue('d');
        final Thread directory = new Thread(
                () -> {
                    File file = new File(iniDirectory);
                    queueFiles.offer(file);
                    while (!endOfList) {
                        File tempFile = queueFiles.poll();
                        if (tempFile != null) {
                            if (tempFile.isDirectory()) {
                                for (File f : Objects.requireNonNull(tempFile.listFiles())) {
                                    queueFiles.offer(f);
                                }
                            } else {
                                fileList.offer(tempFile);
                            }
                        } else {
                            endOfList = true;
                            currentThread().interrupt();
                        }
                    }
                }
        );
        final Thread filteredDir = new Thread(
                () -> {
                    while (!(this.fileList.isEmpty() && endOfList)) {
                        File f = fileList.poll();
                        if (f != null) {
                            int i = f.getName().lastIndexOf('.');
                            String extension = i > 0 ? f.getName().substring(i + 1) : "";
                            for (String e : excludeExtList) {
                                if (!e.equals(extension)) {
                                    filteredFileList.add(f);
                                }
                            }
                        }
                    }
                    currentThread().interrupt();
                }
        );
        directory.start();
        filteredDir.start();
        try {
            directory.join();
            filteredDir.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void exclude() {
        if (cmd.hasOption('e') && cmd.getOptionValues('e') != null) {
            for (String s: cmd.getOptionValues('e')) {
                int i = s.lastIndexOf('.');
                String extension = i > 0 ? s.substring(i + 1) : "";
                excludeExtList.add(extension);
            }
        }
    }

    public void output() {
        filteredFileList.sort((a, b) -> b.toString().compareTo(a.toString()));
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(String.format("%s/%s", iniDirectory, cmd.getOptionValue('o'))));
            FileInputStream fis = new FileInputStream(Objects.requireNonNull(filteredFileList.get(0)).toString());) {
            for (int i = 1; i < filteredFileList.size(); i++) {
                ZipEntry entry1 = new ZipEntry(Objects.requireNonNull(filteredFileList.get(i)).toString());
                zout.putNextEntry(entry1);
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                zout.write(buffer);
                zout.closeEntry();
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     *  String[] testArgs = { "-d", "/home/igor/project/job4j/chapter_009", "-e", "*.xml", "-o", "pack.zip"};
     *  args = testArgs;
     * @param args
     */
    public void commandLineParser(String[] args) {
        Options options = new Options();
        options.addOption(Option.builder("d").hasArg().required().build());
        options.addOption(Option.builder("e").hasArgs().build());
        options.addOption(Option.builder("o").hasArg().required().build());
        CommandLineParser parser = new DefaultParser();
        try {
            this.cmd = parser.parse(options, args);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Args pack = new Args();
        pack.commandLineParser(args);
        pack.exclude();
        pack.directory();
        pack.output();
    }
}