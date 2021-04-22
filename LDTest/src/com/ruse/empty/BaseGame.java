package com.ruse.empty;

import org.lwjgl.opengl.GL11;

import net.lintford.library.GameInfo;
import net.lintford.library.core.LintfordCore;

public class BaseGame extends LintfordCore {

	// ---------------------------------------------
	// Constants
	// ---------------------------------------------

	private static final String WINDOW_TITLE = "LintfordCore Game";
	private static final String APPLICATION_NAME = "unnamed";

	private static final int WINDOW_WIDTH = 640;
	private static final int WINDOW_HEIGHT = 480;

	// ---------------------------------------------
	// Variables
	// ---------------------------------------------

	// ---------------------------------------------
	// Constructor
	// ---------------------------------------------

	public BaseGame(GameInfo pGameInfo, String[] pArgs, boolean pHeadless) {
		super(pGameInfo, pArgs, pHeadless);

	}

	// ---------------------------------------------
	// Core-Methods
	// ---------------------------------------------

	@Override
	protected void oninitializeGL() {
		super.oninitializeGL();

		// Enable depth testing
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		// Enable depth testing
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDepthFunc(GL11.GL_LEQUAL);

		// Set the clear color to corn flower blue
		GL11.glClearColor(0.0f / 255.0f, 149.0f / 255.0f, 237.0f / 255.0f, 1.0f);

	}

	@Override
	protected void onInitializeApp() {
		super.onInitializeApp();

	}

	@Override
	protected void onLoadGLContent() {
		super.onLoadGLContent();

	}

	@Override
	protected void onUpdate() {
		super.onUpdate();

	}

	@Override
	protected void onDraw() {
		super.onDraw();

	}

	// -------------------------------
	// Entry Point
	// -------------------------------

	public static void main(String[] args) {
		GameInfo lGameInfo = new GameInfo() {
			@Override
			public String windowTitle() {
				return WINDOW_TITLE;

			}

			@Override
			public String applicationName() {
				return APPLICATION_NAME;

			}

			@Override
			public int minimumWindowWidth() {
				return WINDOW_WIDTH;
			}

			@Override
			public int minimumWindowHeight() {
				return WINDOW_HEIGHT;
			}

			@Override
			public int baseGameResolutionWidth() {
				return WINDOW_WIDTH;
			}

			@Override
			public int baseGameResolutionHeight() {
				return WINDOW_HEIGHT;
			}

			@Override
			public boolean stretchGameResolution() {
				return false;
			}

			@Override
			public boolean windowResizeable() {
				return false;
			}

		};

		var lBaseGame = new BaseGame(lGameInfo, args, false);
		lBaseGame.createWindow();

	}

}
