package rpsshooter;

public class PlayerControls extends motion.SpriteEntity implements cave.PointerListener
{
	private motion.TextureSprite left = null;
	private motion.TextureSprite right = null;
	private motion.TextureSprite rockBtn = null;
	private motion.TextureSprite paperBtn = null;
	private motion.TextureSprite scissorsBtn = null;
	private motion.TextureSprite controlsSprite = null;
	private rpsshooter.GameScene gameScene = null;
	private double height = 0.00;
	private double width = 0.00;

	static public rpsshooter.GameScene objectAsRpsshooterGameScene(java.lang.Object o) {
		if(o instanceof rpsshooter.GameScene) {
			return((rpsshooter.GameScene)o);
		}
		return(null);
	}

	@Override
	public motion.Sprite createSprite() {
		gameScene = rpsshooter.PlayerControls.objectAsRpsshooterGameScene((java.lang.Object)getScene());
		width = layer.getReferenceWidth();
		height = layer.getReferenceHeight();
		left = motion.SpriteUtil.addTextureSpriteForSize((motion.Scene)scene, layer, ((motion.Scene)scene).createTextureForImageResource("left_unclicked"), 0.13 * width, 0.13 * height);
		right = motion.SpriteUtil.addTextureSpriteForSize((motion.Scene)scene, layer, ((motion.Scene)scene).createTextureForImageResource("right_unclicked"), 0.13 * width, 0.13 * height);
		rockBtn = motion.SpriteUtil.addTextureSpriteForSize((motion.Scene)scene, layer, ((motion.Scene)scene).createTextureForImageResource("rock_shoot"), 0.13 * width, 0.13 * height);
		paperBtn = motion.SpriteUtil.addTextureSpriteForSize((motion.Scene)scene, layer, ((motion.Scene)scene).createTextureForImageResource("paper_shoot"), 0.13 * width, 0.13 * height);
		scissorsBtn = motion.SpriteUtil.addTextureSpriteForSize((motion.Scene)scene, layer, ((motion.Scene)scene).createTextureForImageResource("scissors_shoot"), 0.13 * width, 0.13 * height);
		left.move((double)0, height - left.getHeight());
		right.move(left.getWidth(), height - left.getHeight());
		rockBtn.move(width - rockBtn.getWidth(), height - left.getHeight());
		paperBtn.move(rockBtn.getX() - paperBtn.getWidth(), height - left.getHeight());
		scissorsBtn.move(paperBtn.getX() - scissorsBtn.getWidth(), height - left.getHeight());
		return((motion.Sprite)controlsSprite);
	}

	@Override
	public void cleanup() {
		layer.removeAllSprites();
	}

	public motion.TextureSprite getLeftButton() {
		return(left);
	}

	public motion.TextureSprite getRightButton() {
		return(right);
	}

	public motion.TextureSprite getRockButton() {
		return(rockBtn);
	}

	public motion.TextureSprite getPaperButton() {
		return(paperBtn);
	}

	public motion.TextureSprite getScissorsButton() {
		return(scissorsBtn);
	}

	public boolean onPointerEvent(cave.PointerEvent event) {
		if(event.isConsumed) {
			return(false);
		}
		if(event.action == cave.PointerEvent.DOWN) {
			if(event.isInside(left.getX(), left.getY(), left.getWidth(), left.getHeight())) {
				left.setTextureWithSize(((motion.Scene)scene).createTextureForImageResource("left_clicked"), 0.13 * width, 0.13 * height);
				System.out.println("LEFT BTN");
			}
			else if(event.isInside(right.getX(), right.getY(), right.getWidth(), right.getHeight())) {
				right.setTextureWithSize(((motion.Scene)scene).createTextureForImageResource("right_clicked"), 0.13 * width, 0.13 * height);
				System.out.println("RIGHT BTN");
			}
			else if(event.isInside(rockBtn.getX(), rockBtn.getY(), rockBtn.getWidth(), rockBtn.getHeight())) {
				gameScene.shootRock();
				System.out.println("ROCK BTN");
			}
			else if(event.isInside(paperBtn.getX(), paperBtn.getY(), paperBtn.getWidth(), paperBtn.getHeight())) {
				gameScene.shootPaper();
				System.out.println("PAPER BTN");
			}
			else if(event.isInside(scissorsBtn.getX(), scissorsBtn.getY(), scissorsBtn.getWidth(), scissorsBtn.getHeight())) {
				gameScene.shootScissors();
				System.out.println("SCISSORS BTN");
			}
		}
		else if(event.action == cave.PointerEvent.UP) {
			left.setTextureWithSize(((motion.Scene)scene).createTextureForImageResource("left_unclicked"), 0.13 * width, 0.13 * height);
			right.setTextureWithSize(((motion.Scene)scene).createTextureForImageResource("right_unclicked"), 0.13 * width, 0.13 * height);
		}
		return(false);
	}
}
