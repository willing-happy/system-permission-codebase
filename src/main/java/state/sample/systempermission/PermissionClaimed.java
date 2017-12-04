package state.sample.systempermission;

/**
 * Created by willi on 2017/12/4.
 */
public class PermissionClaimed extends PermissionState {
    public PermissionClaimed() {
        super("CLAIMED");
    }

    public void claimedBy(SystemAdmin admin, SystemPermission permission) {
        return;
    }

    public void deniedBy(SystemAdmin admin,SystemPermission permission) {
        if (!permission.getAdmin().equals(admin))
            return;
        permission.setGranted(false);
        permission.setState(PermissionState.DENIED);
        permission.notifyUserOfPermissionRequestResult();    }
}
