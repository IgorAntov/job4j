package ru.job4j.profession;

public class Doctor extends Profession{

    public boolean heal(Pacient pacient) {
        return false;
    }

    public Diagnose getDiag(Pacient pacient) {
        return new Diagnose();
    }
}
