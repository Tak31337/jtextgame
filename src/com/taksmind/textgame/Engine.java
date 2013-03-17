/*******************************************************************************
 * Copyright (c) 2010 Hippos Development Team
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.opensource.org/licenses/eclipse-1.0.php
 * Contributors:
 *
 *    Hippos Development Team - Java Subnetting Calculator
 *
 ******************************************************************************/
package com.taksmind.textgame;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * 
 * @author tak
 * The core part of the Text Game Engine
 */
public class Engine {
	private Shell shell;
	private Display display;
	private FormLayout layout;
	public List list;
	/*The input text area*/
	private Text input;
	private Button ok;
	private Game game;
	
	/**
	 * 
	 * @param text The title of the game
	 * @param game the game class object.
	 * 
	 * setup the user environment
	 */
	public Engine(String text, Game game) {
				this.game = game;
				display = new Display();
				shell = new Shell(display);
				shell.setText(text);
				
				initUI();
		        
				center(shell);
				shell.open();
	}
	
	/**
	 * Construct the Engines user interface
	 */
	public void initUI() {
		layout = new FormLayout();
		layout.marginWidth = 5;
		layout.marginHeight = 5;
		shell.setLayout(layout);
		
		list = new List(shell, SWT.BORDER | SWT.V_SCROLL | SWT.WRAP );
	
		FormData listData = new FormData();
		listData.right = new FormAttachment(100, 0);
		listData.top = new FormAttachment(0, 0);
		listData.bottom = new FormAttachment(90, -10);
		listData.left = new FormAttachment(0, 0);
		list.setLayoutData(listData);
		
		ok = new Button(shell, SWT.PUSH);
		ok.setText("Okay");
		ok.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String question = list.getItem(list.getItemCount() - 1);
				String text = input.getText();
				list.add(text);
				input.setText("");
				
				game.receiver(question, text);
				question = "";
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				System.out.println("widgetDefauldSelected");
			}
		});
		
		FormData okData = new FormData();
		okData.right = new FormAttachment(100, 0);
		okData.top = new FormAttachment(list, 0);
		okData.bottom = new FormAttachment(100, 0);
		ok.setLayoutData(okData);
		
		input = new Text(shell, SWT.BORDER);
		
		input.addTraverseListener(new TraverseListener() {
			@Override
			public void keyTraversed(TraverseEvent e) {
				if( e.detail == SWT.TRAVERSE_TAB_NEXT ) {
					
				}
			}
		});
		
		FormData inputData = new FormData();
		inputData.left = new FormAttachment(0, 0);
		inputData.right = new FormAttachment( 100, -ok.getBorderWidth() -45);
		inputData.top = new FormAttachment(list, 0);
		inputData.bottom = new FormAttachment(100, 0);
		input.setLayoutData(inputData);
		
		okData.left = new FormAttachment(input, 0);
		
	}
	
	/**
	 * 
	 * @param shell the SWT shell
	 * centers the shell
	 */
	public void center(Shell shell) {
		Rectangle bds = shell.getDisplay().getBounds();
		Point p = shell.getSize();

		int nLeft = (bds.width - p.x) / 2;
		int nTop = (bds.height - p.y) / 2;

		shell.setBounds(nLeft, nTop, p.x, p.y);
	}
	
	/**
	 * 
	 * @param fileName the image file the background will be set to
	 * Set the shell background to given image
	 */
	public void setBackground(String fileName) {
		if ( fileName != null ) {
			Image image = new Image(display, fileName);
			shell.setBackgroundImage(image);
			shell.redraw();
		}
	}
	
	/**
	 * Clear out the list (questions + responses display)
	 */
	public void clear() {
		list.removeAll();
	}
	
	/**
	 * 
	 * @param interaction the question to be asked
	 * add the question to the shell's list
	 */
	public void interact(final String interaction) {
		list.add(interaction);
		shell.redraw();
	}
	
	/**
	 * dispose the display
	 */
	public void dispose() {
		display.dispose();
	}
	
	/**
	 * 
	 * @return the shell
	 * gets the shell
	 */
	public Shell getShell() {
		return shell;
	}
	
	/**
	 * 
	 * @return the display
	 * gets the display
	 */
	public Display getDisplay() {
		return display;
	}
}
