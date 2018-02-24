/******************************************************************************
 * Copyright (C) Devamatre Inc 2009-2018. All rights reserved.
 * 
 * This code is licensed to Devamatre under one or more contributor license 
 * agreements. The reproduction, transmission or use of this code, in source 
 * and binary forms, with or without modification, are permitted provided 
 * that the following conditions are met:
 * 1. Redistributions of source code must retain the above copyright
 * 	  notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE AUTHOR OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
 * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 *      
 * Devamatre reserves the right to modify the technical specifications and or 
 * features without any prior notice.
 *****************************************************************************/
package com.devamatre.designpatterns.structural.flyweight;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created 2016-02-17 10:51:11 AM
 * @version 1.0.0
 * @since 1.0.0
 */
class ColorBox extends Canvas implements Runnable {

	private int pause;
	private Color curColor = getColor();
	private static Color[] colors = { Color.black, Color.blue, Color.cyan, Color.darkGray, Color.gray, Color.green,
			Color.lightGray, Color.red, Color.magenta, Color.orange, Color.pink, Color.white, Color.yellow };

	public ColorBox(int p) {
		pause = p;
		new Thread(this).start();
	}

	private static Color getColor() {
		return colors[(int) (Math.random() * 1000) % colors.length];
	}

	public void run() {
		while (true) {
			curColor = getColor();
			repaint();
			try {
				Thread.sleep(pause);
			} catch (InterruptedException ignored) {
			}
		}
	}

	public void paint(Graphics g) {
		g.setColor(curColor);
		g.fillRect(0, 0, getWidth(), getHeight());
	}
}

/**
 * heavyweight ColorBoxes ==> ColorBox Flyweights and a Factory (1 thread per
 * ColorBox) of pooled HandlerThreads
 * 
 * Discussion. Creating a thread for each ColorBox is a much more straight-
 * forward approach, but it doesn't scale when dozens of ColorBoxes are created.
 * Sharing a "pool" of threads across the collection of ColorBoxes requires more
 * thought to set-up, but does not saturate "system resources" like the former
 * approach does.
 * 
 * In the implementation below, each ColorBox "wraps" itself with a Thread
 * object. The Thread object provides all the "threading functionality magic"
 * and simply calls ColorBox's run() method when it is promoted from the "ready"
 * state to the "running" state. When each Thread/ColorBox is swapped into the
 * CPU, it causes the ColorBox part of itself to change its color and then
 * graciously gives up the CPU [by calling sleep()] so that other Threads may
 * run.
 * 
 * In the ThreadPool implementation, after the ColorBoxes are set-up, the
 * ThreadPool creates and starts 8 HandlerThreads. When a HandlerThread is
 * swapped into the CPU, it gets a random ColorBox object from ThreadPool's
 * private Vector, tells the ColorBox to change its color, and graciously
 * returns to the "asleep" state.
 * 
 * "You can typically make your threaded applications run FASTER by inserting
 * calls to sleep() (with reasonably long durations)." This definitely
 * contributes to the perception that Threads are a "black art". Not enough
 * calls: monopolization of the CPU. Not enough duration: time expiration
 * interrupt events interrupt the running thread before it can finish useful
 * work
 * 
 *
 * @author Rohtash Singh Lakra
 * @date 10/02/2017 12:20:36 PM
 */
public class FlyweightDemo {
	public static void main(String[] args) {
		int size = 8;
		int pause = 100;
		if (args.length > 0) {
			size = Integer.parseInt(args[0]);
		}
		if (args.length > 1) {
			pause = Integer.parseInt(args[1]);
		}
		Frame frame = new Frame("ColorBoxes - 1 thread per ColorBox");
		frame.setLayout(new GridLayout(size, size));
		for (int i = 0; i < size * size; i++) {
			frame.add(new ColorBox(pause));
		}
		frame.setSize(500, 400);
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
}