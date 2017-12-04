package state.sample.systempermission;

/**
 * Created by willi on 2017/12/4.
 */
public class PermissionRequested extends PermissionState {

    public PermissionRequested() {
        super("REQUESTED");
    }

    public void claimedBy(SystemAdmin admin, SystemPermission permission) {
        permission.willBeHandledBy(admin);
        permission.setState(PermissionState.CLAIMED);
    }
}
