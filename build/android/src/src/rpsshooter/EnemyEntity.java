package rpsshooter;

public class EnemyEntity extends motion.SpriteEntity
{
	private motion.Texture rockEnemy = null;
	private motion.Texture paperEnemy = null;
	private motion.Texture scissorsEnemy = null;
	private motion.TextureSprite enemySprite = null;
	private rpsshooter.GameScene gameScene = null;
	private int type = 0;
	private double speed = 0.00;
	private double height = 0.00;
	private double width = 0.00;
	private double rX = 0.00;

	static public rpsshooter.GameScene objectAsRpsshooterGameScene(java.lang.Object o) {
		if(o instanceof rpsshooter.GameScene) {
			return((rpsshooter.GameScene)o);
		}
		return(null);
	}

	@Override
	public motion.Sprite createSprite() {
		gameScene = rpsshooter.EnemyEntity.objectAsRpsshooterGameScene((java.lang.Object)getScene());
		width = layer.getReferenceWidth();
		height = layer.getReferenceHeight();
		rockEnemy = ((motion.Scene)scene).createTextureForImageResource("enemy_rock");
		paperEnemy = ((motion.Scene)scene).createTextureForImageResource("enemy_paper");
		scissorsEnemy = ((motion.Scene)scene).createTextureForImageResource("enemy_scissors");
		cape.Random random = new cape.Random();
		type = (int)random.nextInt((int)3);
		if(type == 1) {
			enemySprite = motion.SpriteUtil.addTextureSpriteForSize((motion.Scene)scene, layer, rockEnemy, 0.10 * width, 0.10 * height);
			// speed = 25
		}
		else if(type == 2) {
			enemySprite = motion.SpriteUtil.addTextureSpriteForSize((motion.Scene)scene, layer, paperEnemy, 0.10 * width, 0.10 * height);
			// speed = 15
		}
		else if(type == 3) {
			enemySprite = motion.SpriteUtil.addTextureSpriteForSize((motion.Scene)scene, layer, scissorsEnemy, 0.10 * width, 0.10 * height);
			// speed = 10
		}
		speed = 3.50;
		rX = (width - enemySprite.getWidth()) * random.nextDouble();
		enemySprite.move(rX, (double)0);
		return((motion.Sprite)enemySprite);
	}

	@Override
	public void cleanup() {
		layer.removeSprite((motion.Sprite)enemySprite);
	}

	public static boolean isCollide(motion.Sprite sprA, motion.Sprite sprB) {
		boolean collide = false;
		double r1 = sprA.getHeight() * 0.40;
		double r2 = sprA.getWidth() * 0.40;
		double totalR = r1 + r2;
		double dx = sprA.getX() - sprB.getX();
		double dy = sprA.getY() - sprB.getY();
		double dr = cape.Math.sqrt((dx * dx) + (dy * dy));
		if(dr < totalR) {
			collide = true;
			System.out.println("COLLIDED");
		}
		return(collide);
	}

	// func checkCollision

	// {

	// }

	@Override
	public void tick(cape.TimeValue gameTime, double delta) {
		super.tick(gameTime, delta);
		enemySprite.move(rX, enemySprite.getY() + (delta / speed));
		if(enemySprite.getY() >= 1) {
			scene.removeEntity((motion.Entity)this);
		}
		if(rpsshooter.EnemyEntity.isCollide((motion.Sprite)gameScene.getProjectile(), sprite)) {
			System.out.println("COLLIDED");
		}
	}
}
