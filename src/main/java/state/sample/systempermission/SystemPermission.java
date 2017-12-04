package state.sample.systempermission;

public class SystemPermission {
    private SystemProfile profile;
    private SystemAdmin admin;
    private boolean granted;
    private boolean isUnixPermissionGranted;
    private PermissionState permissionState;

    public SystemPermission(SystemProfile profile) {
        this.profile = profile;
        this.permissionState = PermissionState.REQUESTED;
        granted = false;
        notifyAdminOfPermissionRequest();
    }

    private void notifyAdminOfPermissionRequest() {

    }

    public void claimedBy(SystemAdmin admin) {
        this.permissionState.claimedBy(admin, this);
    }

    void willBeHandledBy(SystemAdmin admin) {
        this.admin = admin;
    }

    public void deniedBy(SystemAdmin admin) {
        if (!getState().equals(PermissionState.CLAIMED) && !getState().equals(PermissionState.UNIX_CLAIMED))
            return;
        if (!this.admin.equals(admin))
            return;
        granted = false;
        setState(PermissionState.DENIED);
        notifyUserOfPermissionRequestResult();    }

    private void notifyUserOfPermissionRequestResult() {

    }

    public void grantedBy(SystemAdmin admin) {
        if (!getState().equals(PermissionState.CLAIMED) && !getState().equals(PermissionState.UNIX_CLAIMED))
            return;
        if (!this.admin.equals(admin))
            return;

        if (profile.isUnixPermissionRequired() && getState().equals(PermissionState.UNIX_CLAIMED))
            isUnixPermissionGranted = true;
        else if (profile.isUnixPermissionRequired() && !profile.isUnixPermissionGranted()) {
            setState(PermissionState.UNIX_REQUESTED);
            notifyUnixAdminsOfPermissionRequest();
            return;
        }
        setState(PermissionState.GRANTED);
        granted = true;
        notifyUserOfPermissionRequestResult();
    }

    private void notifyUnixAdminsOfPermissionRequest() {

    }

    public String state() {
        return this.getState().toString();
    }

    public boolean isGranted() {
        return this.granted;
    }

    public PermissionState getState() {
        return this.permissionState;
    }

    public void setState(PermissionState state) {
        this.permissionState = state;
    }
}
