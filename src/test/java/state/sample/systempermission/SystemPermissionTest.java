package state.sample.systempermission;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SystemPermissionTest {
    private SystemPermission permission;

    @Before
    public void setUp() {
        SystemProfile profile = new SystemProfile();
        permission = new SystemPermission(profile);
    }

    @Test
    public void shouldChangeStateToGrantedOnlyAfterClaimed() throws Exception {
        SystemAdmin admin = new SystemAdmin();
        permission.claimedBy(admin);
        permission.grantedBy(admin);

        assertEquals("granted", PermissionState.GRANTED.toString(), permission.state());
        assertTrue(permission.isGranted());
    }

    @Test
    public void shouldChangeStateToDeniedAfterClaimed() throws Exception {
        SystemAdmin admin = new SystemAdmin();
        permission.claimedBy(admin);
        permission.deniedBy(admin);

        assertEquals("denied", PermissionState.DENIED.toString(), permission.state());
        assertFalse(permission.isGranted());
    }

    @Test
    public void shouldBeUnixClaimedAfterSystemClaimedAndGrantedWithUnixRequest() throws Exception {
        boolean isUnixPermissionRequired = true;
        SystemProfile profile = new SystemProfile(isUnixPermissionRequired);
        permission = new SystemPermission(profile);
        SystemAdmin admin = new SystemAdmin();

        permission.claimedBy(admin);
        permission.grantedBy(admin);
        assertEquals("Unix requested", PermissionState.UNIX_REQUESTED.toString(), permission.state());
        assertFalse(permission.isGranted());

        permission.claimedBy(admin);
        assertEquals("Unix claimed", PermissionState.UNIX_CLAIMED.toString(), permission.state());

        permission.grantedBy(admin);
        assertEquals("Granted", PermissionState.GRANTED.toString(), permission.state());
    }

    @Test
    public void shouldBeGrantedAfterSystemClaimedAndGrantedAndUnixGrantedWithUnixRequest() throws Exception {
        boolean isUnixPermissionRequired = true;
        SystemProfile profile = new SystemProfile(isUnixPermissionRequired);
        permission = new SystemPermission(profile);
        SystemAdmin admin = new SystemAdmin();

        permission.claimedBy(admin);
        permission.grantedBy(admin);
        assertFalse(permission.isGranted());
        assertEquals("Unix requested", PermissionState.UNIX_REQUESTED.toString(), permission.state());
        assertFalse(permission.isGranted());

        permission.claimedBy(admin);
        assertEquals("Unix claimed", PermissionState.UNIX_CLAIMED.toString(), permission.state());
        assertFalse(permission.isGranted());

        permission.grantedBy(admin);
        assertEquals("Granted", PermissionState.GRANTED.toString(), permission.state());
        assertTrue(permission.isGranted());
    }

    @Test
    public void shouldBeDeniedAfterSystemRequestDeniedWithUnixRequestNeeded() throws Exception {
        boolean isUnixPermissionRequired = true;
        SystemProfile profile = new SystemProfile(isUnixPermissionRequired);
        permission = new SystemPermission(profile);
        SystemAdmin admin = new SystemAdmin();

        permission.claimedBy(admin);
        permission.deniedBy(admin);
        assertEquals("Denied", PermissionState.DENIED.toString(), permission.state());
        assertFalse(permission.isGranted());
    }


    @Test
    public void shouldBeDeniedAfterUnixRequestDeniedWithUnixRequestNeeded() throws Exception {
        boolean isUnixPermissionRequired = true;
        SystemProfile profile = new SystemProfile(isUnixPermissionRequired);
        permission = new SystemPermission(profile);
        SystemAdmin admin = new SystemAdmin();

        permission.claimedBy(admin);
        assertEquals("Claimed", PermissionState.CLAIMED.toString(), permission.state());
        permission.grantedBy(admin);
        assertEquals("Unix Requested", PermissionState.UNIX_REQUESTED.toString(), permission.state());
        assertFalse(permission.isGranted());

        permission.claimedBy(admin);
        permission.deniedBy(admin);
        assertEquals("Denied", PermissionState.DENIED.toString(), permission.state());
        assertFalse(permission.isGranted());
    }

    @Test
    public void shouldHaveRequestedAsInitialState() throws Exception {
        assertEquals("requested", PermissionState.REQUESTED.toString(), permission.state());
    }

    @Test
    public void shouldNotChangeStateToGrantedAfterRequested() throws Exception {
        SystemAdmin admin = new SystemAdmin();
        permission.grantedBy(admin);

        assertEquals("requested", PermissionState.REQUESTED.toString(), permission.state());
        assertFalse(permission.isGranted());
    }

    @Test
    public void shouldNotChangeStateToGrantedWhenItemIsNotHandledByAdminWhoClaimIt() throws Exception {
        SystemAdmin claimAdmin = new SystemAdmin();
        permission.claimedBy(claimAdmin);
        SystemAdmin anotherAdmin = new SystemAdmin();
        permission.grantedBy(anotherAdmin);

        assertEquals("claimed", PermissionState.CLAIMED.toString(), permission.state());
        assertFalse(permission.isGranted());
    }

    @Test
    public void shouldNotChangeStateToDeniedAfterRequested() throws Exception {
        SystemAdmin admin = new SystemAdmin();
        permission.deniedBy(admin);

        assertEquals("requested", PermissionState.REQUESTED.toString(), permission.state());
        assertFalse(permission.isGranted());
    }

    @Test
    public void shouldNotChangeStateToDeniedWhenNotDeniedByClaimedAdmin() throws Exception {
        SystemAdmin claimedAdmin = new SystemAdmin();
        permission.claimedBy(claimedAdmin);
        SystemAdmin anotherAdmin = new SystemAdmin();
        permission.deniedBy(anotherAdmin);

        assertEquals("claimed", PermissionState.CLAIMED.toString(), permission.state());
        assertFalse(permission.isGranted());
    }

    @Test
    public void shouldNotBeAllBeClaimedAfterBeGranted() throws Exception {
        SystemAdmin admin = new SystemAdmin();
        permission.claimedBy(admin);
        permission.claimedBy(admin);

        assertEquals("denied", PermissionState.CLAIMED.toString(), permission.state());
        assertFalse(permission.isGranted());
    }
}