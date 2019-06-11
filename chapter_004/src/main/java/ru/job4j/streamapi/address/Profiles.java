package ru.job4j.streamapi.address;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class Profiles {
    List<Address> collect(List<Profile> profiles) {
      return profiles.stream().map(profile -> profile.getAddress()).collect(Collectors.toList());
    }
}
