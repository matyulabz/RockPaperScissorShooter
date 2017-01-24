package rpsshooter;

public class PlayerEntity extends motion.SpriteEntity implements cave.KeyListener, cave.PointerListener
{
	private motion.Texture normal = null;
	private motion.TextureSprite playerSprite = null;
	private rpsshooter.GameScene gameScene = null;
	private double height = 0.00;
	private double width = 0.00;
	private boolean pressedLeft = false;
	private boolean pressedRight = false;

	static public rpsshooter.GameScene objectAsRpsshooterGameScene(java.lang.Object o) {
		if(o instanceof rpsshooter.GameScene) {
			return((rpsshooter.GameScene)o);
		}
		return(null);
	}

	@Override
	public motion.Sprite createSprite() {
		gameScene = rpsshooter.PlayerEntity.objectAsRpsshooterGameScene((java.lang.Object)getScene());
		width = layer.getReferenceWidth();
		height = layer.getReferenceHeight();
		normal = ((motion.Scene)scene).createTextureForImageResource("normal");
		playerSprite = motion.SpriteUtil.addTextureSpriteForSize((motion.Scene)scene, layer, normal, 0.15 * width, 0.15 * height);
		playerSprite.move(0.50 * width, (height - playerSprite.getHeight()) - gameScene.getControls().getLeftButton().getHeight());
		return((motion.Sprite)playerSprite);
	}

	@Override
	public void cleanup() {
		layer.removeSprite((motion.Sprite)playerSprite);
	}

	public void onKeyEvent(cave.KeyEvent event) {
		java.lang.String value = event.getStringValue();
		if(event.getAction() == 1) {
			if(event.isKey(cave.KeyEvent.KEY_LEFT)) {
				pressedLeft = true;
				System.out.println("LEFT");
			}
			else if(event.isKey(cave.KeyEvent.KEY_RIGHT)) {
				pressedRight = true;
				System.out.println("RIGHT");
			}
			else if(cape.String.equalsIgnoreCase(value, "e")) {
				gameScene.shootRock();
				System.out.println("ROCK");
			}
			else if(cape.String.equalsIgnoreCase(value, "w")) {
				gameScene.shootPaper();
				System.out.println("PAPER");
			}
			else if(cape.String.equalsIgnoreCase(value, "q")) {
				gameScene.shootScissors();
				System.out.println("SCISSORS");
			}
			else {
				System.out.println(("Unhandled key event: `" + event.getStringValue()) + "`");
			}
		}
		if(event.getAction() == 2) {
			pressedLeft = false;
			pressedRight = false;
		}
	}

	public boolean onPointerEvent(cave.PointerEvent event) {
		if(event.isConsumed) {
			return(false);
		}
		double leftX = gameScene.getControls().getLeftButton().getX();
		double leftY = gameScene.getControls().getLeftButton().getY();
		double leftW = gameScene.getControls().getLeftButton().getWidth();
		double leftH = gameScene.getControls().getLeftButton().getHeight();
		double rightX = gameScene.getControls().getRightButton().getX();
		double rightY = gameScene.getControls().getRightButton().getY();
		double rightW = gameScene.getControls().getRightButton().getWidth();
		double rightH = gameScene.getControls().getRightButton().getHeight();
		if(event.action == cave.PointerEvent.DOWN) {
			if(event.isInside(leftX, leftY, leftW, leftH)) {
				pressedLeft = true;
				System.out.println("LEFT CLICK");
			}
			else if(event.isInside(rightX, rightY, rightW, rightH)) {
				pressedRight = true;
				System.out.println("RIGHT CLICK");
			}
		}
		else if(event.action == cave.PointerEvent.UP) {
			pressedLeft = false;
			pressedRight = false;
		}
		return(false);
	}

	@Override
	public void tick(cape.TimeValue gameTime, double delta) {
		super.tick(gameTime, delta);
		if(sprite.getX() < 0) {
			sprite.move((double)0, sprite.getY());
		}
		if((sprite.getX() + sprite.getWidth()) >= width) {
			sprite.move(width - sprite.getWidth(), sprite.getY());
		}
		if(pressedLeft) {
			sprite.move(sprite.getX() - (width * 0.01), sprite.getY());
		}
		else if(pressedRight) {
			sprite.move(sprite.getX() + (width * 0.01), sprite.getY());
		}
	}
}
