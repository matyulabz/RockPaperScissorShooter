package rpsshooter;

public class MainMenu extends motion.SpriteSceneWithEntities
{
	private motion.TextureSprite bg = null;
	private motion.TextButtonEntity playButton = null;
	private double height = 0.00;
	private double width = 0.00;

	@Override
	public void initialize() {
		super.initialize();
		height = getReferenceHeight();
		width = getReferenceWidth();
		bg = addTextureSpriteForSize(createTextureForImageResource("bg"), width, height);
		bg.move((double)0, (double)0);
		playButton = motion.TextButtonEntity.forText("PLAY");
		playButton.setBackgroundColor(cave.Color.forRGB(255, 0, 0));
		addEntity((motion.Entity)playButton);
		playButton.move((0.50 * width) - (playButton.getWidth() * 0.50), 0.75 * height);
		playButton.setClickHandler(new samx.function.Procedure0() {
			public void execute() {
				startGame();
			}
		});
	}

	public void startGame() {
		pushScene((motion.Scene)new rpsshooter.GameScene());
	}

	@Override
	public void cleanup() {
		removeAllSprites();
	}
}
