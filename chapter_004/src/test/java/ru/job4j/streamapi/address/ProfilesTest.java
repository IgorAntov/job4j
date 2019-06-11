package ru.job4j.streamapi.address;

import org.junit.Assert;
import org.junit.Test;
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
       Address address = new Address("city", "street1", 1, 1);
       Profile profile = new Profile(address);
       Profiles profiles = new Profiles();
       List<Profile> profileList = new ArrayList<>();
       profileList.add(profile);
       Assert.assertThat(profiles.collect(profileList).get(0), is(address));
    }

    @Test
    public void collect2() {
        Address address1 = new Address("city1", "street1", 1, 1);
        Address address2 = new Address("city2", "street2", 2, 2);
        Profile profile1 = new Profile(address2);
        Profile profile2 = new Profile(address1);
        Profile profile3 = new Profile(address1);
        Profiles profiles = new Profiles();
        List<Profile> profileList = new ArrayList<>();
        profileList.add(profile1);
        profileList.add(profile2);
        profileList.add(profile3);
        Assert.assertThat(profiles.collect2(profileList).size(), is(2));
        Assert.assertThat(profiles.collect2(profileList).get(0), is(address1));
        Assert.assertThat(profiles.collect2(profileList).get(1), is(address2));
    }
}