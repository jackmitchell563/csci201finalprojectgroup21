import java.util.Date;

public class Guest extends User {
	private Date trialStartDate;
	private static final long TRIAL_DURATION_MS = 24 * 60 * 60 * 1000L; // 1 day
	
    public Guest(String name) {
        super(name, 0, 'U', 0, 0);
        this.trialStartDate = new Date();
    }

    public void accessFreeTrial() {
    	this.trialStartDate = new Date();
    	System.out.println("Welcome, " + getName() + "! Your one-day free trial starts now.");
    }
    
    public boolean hasTrialExpired() {
        long currentTime = System.currentTimeMillis();
        return currentTime > (trialStartDate.getTime() + TRIAL_DURATION_MS);
    }
    
    public void displayTrialStatus() {
        if (hasTrialExpired()) {
            System.out.println("Your one-day trial has expired. Please register for a full account.");
        } else {
            System.out.println("Your trial is still active. Enjoy your free trial!");
        }
    }
    
    public Date getTrialStartDate() {
        return trialStartDate;
    }
    
    // for testing
    public void setTrialStartDate(Date trialStartDate) {
        this.trialStartDate = trialStartDate;
    }

}
