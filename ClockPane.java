import java.util.Calendar;
import java.util.GregorianCalendar;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class ClockPane extends Pane{
	
	private int hour;
	private int minute;
	private int second;
	
	//clock's width and length
	private double w=250,h=250;
	
	//construct a default clockwith the current time
	public ClockPane(){
		setCurrentTime();
	}
	
	//construct a clock with specified hour,minute,second
	public ClockPane(int hour, int minute,int second){
		this.hour = hour;
		this.minute = minute;
		this.second = second;
		paintClock();
	}
	
	//return hour
	public int getHour(){
		return hour;
	}
	
	//set a new hour
	public void setHour(int hour){
		this.hour = hour;
		paintClock();
	}
	
	//return minute
	public int getMinute(){
		return minute;
	}
	
	//set a new minute
	public void setMinute(int minute){
		this.minute = minute;
		paintClock();
	}
	
	//return second
	public int getSecond(){
		return second;
	}
	
	//set a new second
	public void setSecond(int second){
    this.second = second;
    paintClock();
	}
	
	//return clock pane's width
	public double getW(){
		return w;
	}
	
	//set clock pane's width
	public void setW(double w){
		this.w = w;
		paintClock();
	}
	
	//return clock pane's height
	public double getH(){
		return h;
	}
	
	//set clock pane's height
	public void setH(double h){
		this.h = h;
		paintClock();
	}
	
	//set the current time for the clock
	public void setCurrentTime(){
		//construct a calendar for the current time
		Calendar calendar = new GregorianCalendar();
		
		//set current hour,minute,second 
		this.hour = calendar.get(Calendar.HOUR_OF_DAY);
		this.minute = calendar.get(Calendar.MINUTE);
		this.second = calendar.get(Calendar.SECOND);
		
		paintClock();//repaint the clock
	}
	
	//paint the clock
	protected void paintClock(){
		//initialize clock parameters
		double clockRadius = Math.min(w,h)*0.8*0.5;
		double centerX = w/2;
		double centerY = h/2;
		int h = hour;
		int m = minute;
		int s = second;
		Line[] line = new Line[60];
		
		//draw circle
		Circle circle = new Circle(centerX,centerY,clockRadius);
		circle.setFill(Color.WHITE);
		circle.setStroke(Color.BLACK);
		Text t1 = new Text(centerX-5,centerY-clockRadius+12,"12");
		Text t2 = new Text(centerX-clockRadius+3,centerY+5,"9");
		Text t3 = new Text(centerX+clockRadius-10,centerY+3,"3");
		Text t4 = new Text(centerX-3,centerY+clockRadius-3,"6");
		
		//add the marked scale on the clock 
		for (int i =0;i<60;i++){
			double intX = centerX+clockRadius*Math.sin(i*(2*Math.PI/60));
			double intY = centerY-clockRadius*Math.cos(i*(2*Math.PI/60));
			double endX ;
			double endY ;
			if(i==0||i==5||i==10||i==15||i==20||i==25||i==30||i==35||i==40||i==45||i==50||i==55){
				endX = centerX+clockRadius*0.9*Math.sin(i*(2*Math.PI/60));
				endY = centerY-clockRadius*0.9*Math.cos(i*(2*Math.PI/60));
			}
			else{
				endX = centerX+clockRadius*0.95*Math.sin(i*(2*Math.PI/60));
				endY = centerY-clockRadius*0.95*Math.cos(i*(2*Math.PI/60));
			}
			line[i] = new Line(intX,intY,endX,endY);
		}
		
		//draw second hand
		double sLength = clockRadius*0.8;
		double secondX = centerX+sLength*Math.sin(second*(2*Math.PI/60));
		double secondY = centerY-sLength*Math.cos(second*(2*Math.PI/60));
		Line sLine = new Line(centerX,centerY,secondX,secondY);
		sLine.setStroke(Color.RED);
		
		//draw minute hand
		double mLength = clockRadius*0.65;
		double minuteX = centerX+mLength*Math.sin(minute*(2*Math.PI/60));
		double minuteY = centerY-mLength*Math.cos(minute*(2*Math.PI/60));
		Line mLine = new Line(centerX,centerY,minuteX,minuteY);
		sLine.setStroke(Color.BLUE);
		
		//draw hour hand
		double hLength = clockRadius*0.5;
		double hourX = centerX+hLength*Math.sin((hour%12+minute/60.0)*(2*Math.PI/12));
		double hourY= centerY-hLength*Math.cos((hour%12+minute/60.0)*(2*Math.PI/12));
		Line hLine = new Line(centerX,centerY,hourX,hourY);
		hLine.setStroke(Color.GREEN);
		
		//add the label below the clock
		Label l = new Label(h+":"+m+":"+s);
		l.setPrefSize(100,25);
		l.setLayoutX(100);
		l.setLayoutY(250);
		
		
		getChildren().clear();
		getChildren().addAll(circle,t1,t2,t3,t4,sLine,mLine,hLine,l);
		for(int i=0;i<60;i++){	
			getChildren().addAll(line[i]);
		}	
	}
}
