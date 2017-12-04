package state.sample.systempermission;

/**
 * Created by willi on 2017/12/4.
 */
public class UnixPermissionClaimed extends PermissionState{
    public UnixPermissionClaimed() {
        super("UNIX_CLAIMED");
    }

    public void claimedBy(SystemAdmin admin, SystemPermission permission) {
        return;
    }
}
