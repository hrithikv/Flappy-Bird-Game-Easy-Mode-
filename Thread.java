package com.ziyue;

import java.awt.Color;  
import javax.swing.JOptionPane;

public class MyThread extends Thread {
	private int width_val = 10;
	private int height_val = 10;
	private int x, y;
	private  java.awt.Graphics g;
        private GameUI UI;
      	private boolean isTrue = true;
	private MyListener listener;
	private int rate;
	private int x1 = 5, y1 = 5;  //ball movement speed
	private long start, end;
	private long last_time;
	private int response;
      	private boolean isDone = true;
      	private boolean isPause = true;
      	private java.util.ArrayList<MyThread> test_list;
      	private float value = 100;
      	private java.awt.Color color = java.awt.Color.orange;

	      public MyThread(java.awt.Graphics g, MyListener listener, GameUI UI, int x,
	                  		int y, int rate) {
	              this.g = g;
	            	this.UI = UI;
	            	this.x = x;
            		this.y = y;
            		this.rate = rate;
            		this.listener = listener;
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
	                  			x += x1;
	                  			y += y1;

	                  			getColor();
	                  			g.setColor(color);
	                   			g.fillOval(x, y, width_val, height_val);
			                  	}
			                   	int x2 = listener.getX();
			                  	if (x > 280) {
		                         			x1 = -5;
                   				}
                  				if (x < 5) {
                         					x1 = 5;
                   				}
                  				if (y < 80) {
                        					y1 = 5;
                  				}
                  				if (y > 615 && x > x2 && x < x2 + 100) {
                         					y1 = -5;
                   				}
                  				if (y > 730) {
                        					if (isTrue) {
                      						isAgain();
				                        	}
				                	        stopThread();
				                  }
				                  try {
				                        	Thread.sleep(rate);
					                        value -= 0.1;
				                  } catch (Exception ef) {
					                        ef.printStackTrace();
				                  }
				
                  				end = System.currentTimeMillis();
                  				last_time = 100 - (end - start) / 1000;
                  				UI.text_field.setText(last_time + "s");
	                  			UI.pBar.setValue((int) value);
                   				if (last_time == 0) {
                        					test_list = UI.test_list;
                        					for (int j = 0; j < test_list.size(); j++) {
                            						test_list.get(j).stopThread();
                            						test_list.get(j).fadeOval();

                        					}
                        					stopThread();
                        					showConvo();
                  				}
             			}

		          }
         
      	}

	
      	public void fadeOval() {
             		g.setColor(java.awt.Color.DARK_GRAY);
		            g.fillOval(x, y, width_val, height_val);
	      }

	      public void isAgain() {
		            isTrue = false;
		            test_list = UI.test_list;
		            //System.out.println(list.size());

	            	for (int j = 0; j < test_list.size(); j++) {
                  		  test_list.get(j).stopThread();
		                  	test_list.get(j).fadeOval();

             		}
	            	Object[] options = { "Yes", "No" };
	             	String test_command = UI.getCommand();
	            	response = JOptionPane.showOptionDialog(null,
			                         	"You lost><\nWanna try again?", null, JOptionPane.YES_OPTION,
			                        	JOptionPane.NO_OPTION, null, options, null);

             		//System.out.println(response);
	             	if (response == 0) {
	                  		if (test_command.equals("Easy") || test_command.equals("Start")) {
			                        	AgainThread();
		                        		if (test_list.size() != 0) {
				                              	test_list.removeAll(test_list);
			                              		UI.creatBall(10, 1);
		                         		}

                }
		          	if (test_command.equals("Medium")) {
		                 		AgainThread();
				                if (test_list.size() != 0) {
					                      test_list.removeAll(test_list);
					                      UI.creatBall(10, 2);
				                }
			          }
  
	          		if (test_command.equals("Hard")) {
				                AgainThread();
				                if (test_list.size() != 0) {
					                      test_list.removeAll(test_list);
                                UI.creatBall(10, 3);
		                		}
		          	}
		    }

		            if (response == -1 || response == 1) {
		           	        test_list.removeAll(test_list);
		            }

	      }

      	public void stopThread() {
             		  isDone = false;
		          isPause = false;

      	}

      	public void PauseThread() {
		          isPause = false;

	      }

      	public void ContinueThread() {
   		          isPause = true;
      	}

	public void AgainThread() {
		          isDone = true;
	            	  isPause = true;
      	}
	public void showConvo() {
             		javax.swing.JOptionPane.showInputDialog("Please enter the name.\n");
      	}

      	public void getColor() {
			          color = java.awt.Color.black;
	      }
}
