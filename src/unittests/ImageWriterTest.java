/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import primitives.Color;
import renderer.ImageWriter;

/**
 * @author shimo
 *
 */
public class ImageWriterTest {

	/**
	 * Test method for {@link renderer.ImageWriter#writeToImage()}.
	 */
	@Test
	public void testWriteToImage() {
		Color color = new Color(java.awt.Color.blue);
		ImageWriter image = new ImageWriter("imageTest", 16, 10);
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 16; j++) {
				image.writePixel(j, i, color);
			}
	}

	/**
	 * Test method for
	 * {@link renderer.ImageWriter#writePixel(int, int, primitives.Color)}.
	 */
	/*
	 * @Test public void testWritePixel() { fail("Not yet implemented"); }
	 */
}
