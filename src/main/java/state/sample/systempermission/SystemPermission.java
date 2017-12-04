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
        setGranted(false);
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
        this.permissionState.deniedBy(admin, this);
    }

    void notifyUserOfPermissionRequestResult() {

    }

    public void grantedBy(SystemAdmin admin) {
        this.permissionState.grantedBy(admin, this);
    }

    void notifyUnixAdminsOfPermissionRequest() {

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

    public SystemAdmin getAdmin() {
        return admin;
    }

    public void setGranted(boolean granted) {
        this.granted = granted;
    }

    public SystemProfile getProfile() {
        return profile;
    }

    public void setUnixPermissionGranted(boolean unixPermissionGranted) {
        isUnixPermissionGranted = unixPermissionGranted;
    }
}
