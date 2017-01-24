package rpsshooter;

public class ProjectileEntity extends motion.SpriteEntity
{
	private motion.Texture rock = null;
	private motion.Texture paper = null;
	private motion.Texture scissors = null;
	private motion.TextureSprite projectile = null;
	private rpsshooter.GameScene gameScene = null;
	private double height = 0.00;
	private double width = 0.00;
	private double speed = 0.00;
	private double x = 0.00;
	private double y = 0.00;
	private int type = 0;

	// var pressedRock = false

	// var pressedPaper = false

	// var pressedScissors = false

	public ProjectileEntity(int t) {
		type = t;
	}

	@Override
	public motion.Sprite createSprite() {
		width = layer.getReferenceWidth();
		height = layer.getReferenceHeight();
		gameScene = (rpsshooter.GameScene)((scene instanceof rpsshooter.GameScene) ? scene : null);
		speed = 3.50;
		rock = ((motion.Scene)scene).createTextureForImageResource("rock");
		paper = ((motion.Scene)scene).createTextureForImageResource("paper");
		scissors = ((motion.Scene)scene).createTextureForImageResource("scissors");
		// projectile = gameScene.addTextureSpriteForSize(rock, 0.1 * width, 0.1 * height)
		x = gameScene.getPlayer().getX() + (0.20 * gameScene.getPlayer().getWidth());
		y = gameScene.getPlayer().getY();
		if(type == 1) {
			projectile = motion.SpriteUtil.addTextureSpriteForSize((motion.Scene)scene, layer, rock, 0.10 * width, 0.10 * height);
		}
		else if(type == 2) {
			projectile = motion.SpriteUtil.addTextureSpriteForSize((motion.Scene)scene, layer, paper, 0.10 * width, 0.10 * height);
		}
		else if(type == 3) {
			projectile = motion.SpriteUtil.addTextureSpriteForSize((motion.Scene)scene, layer, scissors, 0.10 * width, 0.10 * height);
		}
		projectile.move(x, y);
		System.out.println("SHOOT");
		return((motion.Sprite)projectile);
	}

	@Override
	public void cleanup() {
		layer.removeSprite((motion.Sprite)projectile);
	}

	@Override
	public void tick(cape.TimeValue gameTime, double delta) {
		super.tick(gameTime, delta);
		projectile.move(projectile.getX(), projectile.getY() - (delta / speed));
		if(projectile.getY() <= 0) {
			scene.removeEntity((motion.Entity)this);
		}
	}

	public rpsshooter.ProjectileEntity getProjectile() {
		return(this);
	}

	public int getType() {
		System.out.println(type);
		return(type);
	}
}
