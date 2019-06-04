import java.awt.Color;  
import javax.swing.JOptionPane;

public class MyThread extends Thread {
	private int width_val = 10;
	private int height_val = 10;
	private int x;
	private int y;
	private  java.awt.Graphics g;
        private GameUI game_UI;
      	private boolean isTrue = true;
	private MyListener sys_listnr;
	private int rate;
	private int x1 = 5; 
	private int y1 = 5; 
	private long start;
	private long end;
	private string low = "easy"
	private string mid = "medium"
	private string diff = "hard"
	private long last_time;
	private int sys_response;
      	private boolean isDone = true;
      	private boolean isPause = true;
      	private java.util.ArrayList<MyThread> test_list;
      	private float value = 100;
      	private java.awt.Color color = java.awt.Color.orange;

	      public MyThread(java.awt.Graphics g, MyListener sys_listnr, GameUI game_UI, int x, int y, int rate) {
	              this.g = g;
	            	this.game_UI = game_UI;
	            	this.x = x;
            		this.y = y;
            		this.rate = rate;
            		this.sys_listnr = sys_listnr;
	      }

      	public void run() {
            		drawOval();
 }

	      public void drawOval() {
		
		
		    start = System.currentTimeMillis();
		    while (isDone) {
			           while (isPause) {
				                  synchronized(this.g){
			                  	g.setColor(java.awt.Color.DARK_GRAY);
			                  	g.fillOval(x, y, width_val, height_val);
	                  			x = x + x1;
	                  			y = y + y1;

	                  			getColor();
	                  			g.setColor(color);
	                   			g.fillOval(x, y, width_val, height_val);
			                  	}
			                   	int x2 = sys_listnr.getX();
			                  	if (x > 280) {
		                         			x1 = - 6;
                   				}
                  				if (x < 5) {
                         					x1 = 6;
                   				}
                  				if (y < 80) {
                        					y1 = 6;
                  				}
                  				if (y > 615 && x > x2 && x < x2 + 100) {
                         					y1 = -6;
                   				}
                  				if (y > 730) {
                        					if (isTrue) {
                      						isAgain();
				                        	}
				                	        stop();
				                  }
				                  try {
				                        	Thread.sleep(rate);
					                        value -= 0.1;
				                  } catch (Exception exc) {
					                        exc.printStackTrace();
				                  }
				
                  				end = System.currentTimeMillis();
					   	new_val_found = end - startl;
                  				last_time_foul = 100 - (new_val_found);
					   	last_time = last_time_foul/ 1000
                  				game_UI.text_field.setText(last_time + "s");
	                  			game_UI.pBar.setValue((int) value);
                   				if (last_time == 0) {
                        					test_list = game_UI.test_list;
                        					for (int j = 0; j < test_list.size(); j++) {
                            						test_list.get(j).stop();
                            						test_list.get(j).fadeOval();

                        					}
                        					stop();
                        					showConvo();
                  				}
             			}

		          }
         
      	}

	

	      public void isAgain() {
		            isTrue = false;
		            test_list = game_UI.test_list;
		            //System.out.println(list.size());

	            	for (int j = 0; j < test_list.size(); j++) {
                  		  test_list.get(j).stop();
		                  	test_list.get(j).fadeOval();

             		}
	            	Object[] options = { "Yes", "No" };
	             	String test_command = game_UI.getCommand();
	            	sys_response = JOptionPane.showOptionDialog(null,"You lost the turn><\nPlay again?", null, JOptionPane.YES_OPTION, JOptionPane.NO_OPTION, null, options, null);

	             	if (sys_response == 0) {
	                  		if (test_command.equals(low) || test_command.equals("Start")) {
			                        	Again();
		                        		if (test_list.size() != 0) {
				                              	test_list.removeAll(test_list);
			                              		game_UI.creatBall(10, 1);
		                         		}

                }
		          	if (test_command.equals(mid)) {
		                 		Again();
				                if (test_list.size() != 0) {
					                      test_list.removeAll(test_list);
					                      game_UI.creatBall(10, 2);
				                }
			          }
  
	          		if (test_command.equals(diff)) {
				                Again();
				                if (test_list.size() != 0) {
					                      test_list.removeAll(test_list);
                                game_UI.creatBall(10, 3);
		                		}
		          	}
		    }

		            if (sys_response == -1) {
		           	        test_list.removeAll(test_list);
		            }
		      	    if (sys_response == 1){
				    test_list.removeAll(test_list);
			    }

	      }
      	public void fadeOval() {
             		g.setColor(java.awt.Color.DARK_GRAY);
		            g.fillOval(x, y, width_val, height_val);
	      }

	
      	public void Pause() {
		          isPause = false;

	      }

      	public void Continue() {
   		          isPause = true;
      	}

	public void stop() {
             		  isDone = false;
		          isPause = false;

      	}


	public void Again() {
		          isDone = true;
	            	  isPause = true;
      	}
	public void showConvo() {
		String val_to_do = "Please enter the name"
             		javax.swing.JOptionPane.showInputDialog(val_to_do);
      	}

      	public void getColor() {
			          color = java.awt.Color.black;
	      }
}
