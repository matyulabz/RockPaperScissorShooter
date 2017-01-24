package rpsshooter;

public class GameScene extends motion.SpriteSceneWithEntities
{
	private motion.TextButtonEntity backButton = null;
	private rpsshooter.PlayerEntity player = null;
	private rpsshooter.PlayerControls controls = null;
	private rpsshooter.EnemyEntity enemy = null;

	// var rock as ProjectileEntity

	// var paper as ProjectileEntity

	// var scissors as ProjectileEntity

	private rpsshooter.ProjectileEntity projectile = null;
	private double height = 0.00;
	private double width = 0.00;
	private int time = 0;

	// var pressedLeft = false

	// var pressedRight = false

	@Override
	public void initialize() {
		super.initialize();
		height = getReferenceHeight();
		width = getReferenceWidth();
		backButton = motion.TextButtonEntity.forText("QUIT");
		backButton.setBackgroundColor(cave.Color.forRGB(255, 0, 0));
		addEntity((motion.Entity)backButton);
		time = 0;
		backButton.move((double)0, (double)0);
		backButton.setClickHandler(new samx.function.Procedure0() {
			public void execute() {
				quitGame();
			}
		});
		controls = new rpsshooter.PlayerControls();
		addEntity((motion.Entity)controls);
		player = new rpsshooter.PlayerEntity();
		addEntity((motion.Entity)player);
	}

	public void quitGame() {
		pushScene((motion.Scene)new rpsshooter.MainMenu());
	}

	public cape.ApplicationContext getContext() {
		return((cape.ApplicationContext)context);
	}

	public rpsshooter.PlayerEntity getPlayer() {
		return(player);
	}

	public rpsshooter.PlayerControls getControls() {
		return(controls);
	}

	public rpsshooter.ProjectileEntity getProjectile() {
		return(projectile);
	}

	// func getPaper as ProjectileEntity

	// {

	// return paper

	// }

	// func getScissors as ProjectileEntity

	// {

	// return scissors

	// }

	@Override
	public void cleanup() {
		removeAllSprites();
	}

	public void shootRock() {
		rpsshooter.ProjectileEntity rock = new rpsshooter.ProjectileEntity(1);
		addEntity((motion.Entity)rock);
	}

	public void shootPaper() {
		rpsshooter.ProjectileEntity paper = new rpsshooter.ProjectileEntity(2);
		addEntity((motion.Entity)paper);
	}

	public void shootScissors() {
		rpsshooter.ProjectileEntity scissors = new rpsshooter.ProjectileEntity(3);
		addEntity((motion.Entity)scissors);
	}

	@Override
	public void tick(cape.TimeValue gameTime, double delta) {
		super.tick(gameTime, delta);
		time++;
		if((time % 250) == 0) {
			addEntity((motion.Entity)(enemy = new rpsshooter.EnemyEntity()));
		}
	}
}
