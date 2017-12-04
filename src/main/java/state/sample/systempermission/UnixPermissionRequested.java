package state.sample.systempermission;

/**
 * Created by willi on 2017/12/4.
 */
public class UnixPermissionRequested extends PermissionState {
    public UnixPermissionRequested() {
        super("UNIX_REQUESTED");
    }

    public void claimedBy(SystemAdmin admin, SystemPermission permission) {
        permission.willBeHandledBy(admin);
        permission.setState(PermissionState.UNIX_CLAIMED);
    }
}
