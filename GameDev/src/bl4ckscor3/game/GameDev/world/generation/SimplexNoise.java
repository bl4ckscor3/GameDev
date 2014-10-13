package bl4ckscor3.game.GameDev.world.generation;

import java.util.Random;

import bl4ckscor3.game.GameDev.game.Game;

public class SimplexNoise
{
	private SimplexNoiseOctave[] octaves;
	private double[] frequencies;
	private double[] amplitudes;
	
	//random terrain generation
	public SimplexNoise(int numberOfOctaves, double persistance)
	{
		Random r = new Random(Game.seed); //making sure that the random numbers asked for are always the same (sudo-random aka calculated random)
		
		octaves = new SimplexNoiseOctave[numberOfOctaves];
		frequencies = new double[numberOfOctaves];
		amplitudes = new double[numberOfOctaves];
		
		for(int i = 0; i < numberOfOctaves; i++)
		{
			octaves[i] = new SimplexNoiseOctave(r.nextInt());
			frequencies[i] = Math.pow(2, i);
			amplitudes[i] = Math.pow(persistance, octaves.length - i);
		}
	}
}
