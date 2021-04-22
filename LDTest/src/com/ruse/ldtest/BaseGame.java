package com.ruse.ldtest;

import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.opengl.GL11.glClearColor;

import org.lwjgl.opengl.GL11;

import net.lintford.library.GameInfo;
import net.lintford.library.core.LintfordCore;
import net.lintford.library.core.graphics.ColorConstants;
import net.lintford.library.core.graphics.fonts.FontManager.FontUnit;
import net.lintford.library.core.graphics.sprites.SpriteInstance;
import net.lintford.library.core.graphics.sprites.spritebatch.SpriteBatch;
import net.lintford.library.core.graphics.sprites.spritesheet.SpriteSheetDefinition;
import net.lintford.library.core.graphics.textures.texturebatch.TextureBatchPCT;
import net.lintford.library.screenmanager.ScreenManager;

public class BaseGame extends LintfordCore {

	// ---------------------------------------------
	// Constants
	// ---------------------------------------------

	private static final String WINDOW_TITLE = "Test Application";
	private static final String APPLICATION_NAME = "Test";

	private static final int WINDOW_WIDTH = 640;
	private static final int WINDOW_HEIGHT = 480;

	// ---------------------------------------------
	// Variables
	// ---------------------------------------------

	private ScreenManager mScreenManager;
	private final float mLogoMinimalLoadTime = 3000;
	private float mLogoTimer;

	private FontUnit mTestFont;

	SpriteSheetDefinition mCharacterSpriteSheet;
	SpriteInstance mDwarfSprite;

	SpriteBatch mSpriteBatch;

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
	protected void showStartUpLogo(long pWindowHandle) {
		final var lTexture = mResourceManager.textureManager().loadTexture("LOGO_TEXTURE",
				"res//textures/textureLogo.png", LintfordCore.CORE_ENTITY_GROUP_ID);

		mLogoTimer = System.currentTimeMillis();

		glClearColor(0f, 0f, 0f, 1f);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

		final var lTextureBatch = new TextureBatchPCT();
		lTextureBatch.loadGLContent(mResourceManager);
		lTextureBatch.begin(mHUD);
		lTextureBatch.draw(lTexture, 0, 0, 256, 256, -128, -128, 256, 256, -0.1f, ColorConstants.WHITE);
		lTextureBatch.end();
		lTextureBatch.unloadGLContent();

		glfwSwapBuffers(pWindowHandle);

	}

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

		mTestFont = mResourceManager.fontManager().getFont("FONT_CORE_HEADER");

		mCharacterSpriteSheet = mResourceManager.spriteSheetManager()
				.loadSpriteSheet("res//spritesheets//spritesheetCharacter.json", LintfordCore.CORE_ENTITY_GROUP_ID);
		mDwarfSprite = mCharacterSpriteSheet.getSpriteInstance("walk");

		mSpriteBatch = new SpriteBatch();
		mSpriteBatch.loadGLContent(mResourceManager);
	}

	@Override
	protected void onUpdate() {
		super.onUpdate();

		mDwarfSprite.update(this);

	}

	@Override
	protected void onDraw() {
		super.onDraw();

		// Clear the depth buffer and color buffer
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

		final var lHudRect = mHUD.boundingRectangle();

		mTestFont.begin(mHUD);
		mTestFont.draw("Hello Dwarf", lHudRect.left() + 10.f, lHudRect.top() + 10.f, -0.1f, ColorConstants.BLACK, 1.f);
		mTestFont.end();

		mSpriteBatch.begin(mHUD);
		mSpriteBatch.draw(mCharacterSpriteSheet, mDwarfSprite, -.1f, ColorConstants.WHITE);
		mSpriteBatch.end();

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
