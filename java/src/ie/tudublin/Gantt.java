package ie.tudublin;

import java.util.ArrayList;
import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

public class Gantt extends PApplet
{	
	ArrayList<Task> tasks = new ArrayList<Task>();
	
	float chartBorder;
    float tasksList;

	float w;
    float h;
	
	public void settings()
	{
		size(800, 600);
		
		tasksList = width * 0.03f;
		chartBorder = width * 0.15f;
		h = 30;
	}

	public void loadTasks()
	{
		Table table = loadTable("tasks.csv", "header");
        for(TableRow row:table.rows())
        {
            Task t = new Task(row);
            tasks.add(t);
        }
	}

	public void printTasks()
	{
		for(Task t:tasks)
        {
            System.out.println(t);
        }
	}
	
	public void displayTasks()
	{
		int gap = 50;
		int chartLength = 30;
		int col = 0;
		
		for(int i = 1; i < chartLength+1; i++)
		{
			fill(255);
			text(i, map(i, 1, chartLength+1, chartBorder, width-15), 10);
			stroke(255);
			line(map(i, 1, chartLength+1, chartBorder, width-15), 15, map(i, 1, chartLength+1, chartBorder, width-15), height-30);
		}
		
		for(Task t:tasks)
		{
			fill(255);
			text(t.getTask(), tasksList, gap);
			fill(map(col, 0, tasks.size(), 0, 255), 255, 255);
			noStroke();
			rect(map(t.getStart(), 1, chartLength+1, chartBorder, width-15), gap-15, map((t.getEnd()-t.getStart()), 0, chartLength, 0, width-chartBorder), h);
			gap+=50;
			col++;
		}
	}
	
	public void mousePressed()
	{
		println("Mouse pressed");
		
	}

	public void mouseDragged()
	{
		println("Mouse dragged"); 
		
    }
    
	public void setup() 
	{
		loadTasks();
        printTasks();
		colorMode(HSB);
	}
	
	public void draw()
	{		
		background(0);
		displayTasks();
	}
}
