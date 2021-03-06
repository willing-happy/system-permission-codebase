package state.sample.systempermission;

import static sun.misc.VM.getState;

/**
 * Created by willi on 2017/12/4.
 */
public abstract class PermissionState {
    public final static PermissionState REQUESTED = new PermissionRequested();
    public final static PermissionState CLAIMED = new PermissionClaimed();
    public final static PermissionState GRANTED = new PermissionGranted();
    public final static PermissionState DENIED = new PermissionDenied();
    public static final PermissionState UNIX_REQUESTED = new UnixPermissionRequested();
    public static final PermissionState UNIX_CLAIMED = new UnixPermissionClaimed();

    private String name;

    public PermissionState(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public void claimedBy(SystemAdmin admin, SystemPermission permission){};

    public void deniedBy(SystemAdmin admin,SystemPermission permission) {}

    public void grantedBy(SystemAdmin admin, SystemPermission permission) {}
}
