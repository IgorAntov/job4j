package ru.job4j.streamapi.address;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.streamapi.address.Address;
import ru.job4j.streamapi.address.Profile;
import ru.job4j.streamapi.address.Profiles;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class ProfilesTest {

    @Test
    public void collect() {
       Address address = new Address("city");
       Profile profile = new Profile(address);
       Profiles profiles = new Profiles();
       List<Profile> profileList = new ArrayList<>();
       profileList.add(profile);
       Assert.assertThat(profiles.collect(profileList).get(0), is(address));
    }
}