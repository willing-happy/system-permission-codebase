package state.sample.systempermission;

/**
 * Created by willi on 2017/12/4.
 */
public class PermissionState {
    public final static PermissionState REQUESTED = new PermissionState("REQUESTED");
    public final static PermissionState CLAIMED = new PermissionState("CLAIMED");
    public final static PermissionState GRANTED = new PermissionState("GRANTED");
    public final static PermissionState DENIED = new PermissionState("DENIED");
    public static final PermissionState UNIX_REQUESTED = new PermissionState("UNIX_REQUESTED");
    public static final PermissionState UNIX_CLAIMED = new PermissionState("UNIX_CLAIMED");

    private String name;

    public PermissionState(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
