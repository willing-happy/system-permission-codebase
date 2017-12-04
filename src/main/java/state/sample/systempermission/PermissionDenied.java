package state.sample.systempermission;

/**
 * Created by willi on 2017/12/4.
 */
public class PermissionDenied extends PermissionState {
    public PermissionDenied() {
        super("DENIED");
    }

    public void claimedBy(SystemAdmin admin, SystemPermission permission) {
        return;
    }
}
