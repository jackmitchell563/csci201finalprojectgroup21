import java.util.Date;

public class Exercise {
    private int id;
    private int userId;
    private Date date;
    private String name;
    private int repetitions;
    private int sets;
    private int durationMins;
    
	public Exercise(int id, int userId, Date date, String name, int repetitions, int sets, int durationMins) {
		this.id = id;
		this.userId = userId;
		this.date = date;
		this.name = name;
		this.repetitions = repetitions;
		this.sets = sets;
		this.durationMins = durationMins;
	}
    

}
