package state.sample.systempermission;

public class SystemPermission {
    private SystemProfile profile;
    private SystemAdmin admin;
    private boolean granted;
    private boolean isUnixPermissionGranted;
    private String state;

    public SystemPermission(SystemProfile profile) {
        this.profile = profile;
        state = PermissionState.REQUESTED.toString();
        granted = false;
        notifyAdminOfPermissionRequest();
    }

    private void notifyAdminOfPermissionRequest() {

    }

    public void claimedBy(SystemAdmin admin) {
        if (!state.equals(PermissionState.REQUESTED.toString()) && !state.equals(PermissionState.UNIX_REQUESTED.toString()))
            return;
        willBeHandledBy(admin);
        if (state.equals(PermissionState.REQUESTED.toString()))
            state = PermissionState.CLAIMED.toString();
        else if (state.equals(PermissionState.UNIX_REQUESTED.toString()))
            state = PermissionState.UNIX_CLAIMED.toString();
    }

    private void willBeHandledBy(SystemAdmin admin) {
        this.admin = admin;
    }

    public void deniedBy(SystemAdmin admin) {
        if (!state.equals(PermissionState.CLAIMED.toString()) && !state.equals(PermissionState.UNIX_CLAIMED.toString()))
            return;
        if (!this.admin.equals(admin))
            return;
        granted = false;
        state = PermissionState.DENIED.toString();
        notifyUserOfPermissionRequestResult();    }

    private void notifyUserOfPermissionRequestResult() {

    }

    public void grantedBy(SystemAdmin admin) {
        if (!state.equals(PermissionState.CLAIMED.toString()) && !state.equals(PermissionState.UNIX_CLAIMED.toString()))
            return;
        if (!this.admin.equals(admin))
            return;

        if (profile.isUnixPermissionRequired() && state.equals(PermissionState.UNIX_CLAIMED.toString()))
            isUnixPermissionGranted = true;
        else if (profile.isUnixPermissionRequired() && !profile.isUnixPermissionGranted()) {
            state = PermissionState.UNIX_REQUESTED.toString();
            notifyUnixAdminsOfPermissionRequest();
            return;
        }
        state = PermissionState.GRANTED.toString();
        granted = true;
        notifyUserOfPermissionRequestResult();
    }

    private void notifyUnixAdminsOfPermissionRequest() {

    }

    public String state() {
        return this.state;
    }

    public boolean isGranted() {
        return this.granted;
    }
}
