package state.sample.systempermission;

/**
 * Created by willi on 2017/12/4.
 */
public class PermissionClaimed extends PermissionState {
    public PermissionClaimed() {
        super("CLAIMED");
    }

    public void deniedBy(SystemAdmin admin,SystemPermission permission) {
        if (!permission.getAdmin().equals(admin))
            return;
        permission.setGranted(false);
        permission.setState(PermissionState.DENIED);
        permission.notifyUserOfPermissionRequestResult();    }

    public void grantedBy(SystemAdmin admin, SystemPermission permission) {
        if (!permission.getAdmin().equals(admin))
            return;
        if (permission.isUnixPermissionRequired() && !permission.isUnixPermissionGranted()) {
            permission.setState(PermissionState.UNIX_REQUESTED);
            permission.notifyUnixAdminsOfPermissionRequest();
            return;
        }
        permission.setState(PermissionState.GRANTED);
        permission.setGranted(true);
        permission.notifyUserOfPermissionRequestResult();
    }
}
