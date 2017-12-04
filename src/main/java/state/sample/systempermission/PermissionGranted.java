package state.sample.systempermission;

/**
 * Created by willi on 2017/12/4.
 */
public class PermissionGranted extends PermissionState {
    public PermissionGranted() {
        super("GRANTED");
    }

    public void claimedBy(SystemAdmin admin, SystemPermission permission) {
        return;
    }
}
