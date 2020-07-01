package com.solution.x.search.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * @author Tharinda Wickramaarachchi
 * @since 5/1/2020 10:41 PM
 */
public class JAvaTest
{
	public static void main( String[] args )
	{
		var dsa = 4 + 6;

		boolean blank = "Marco".isBlank();
		Stream<String> lines = "Mar\nco".lines();
		lines.forEach( System.out::println );
		String strip = "Marco  ".strip();

		Path path = null;
		try
		{
			path = Files.writeString( Files.createTempFile( "helloworld", ".txt" ), "Hi, my name is!" );
			String s = Files.readString( path );
		}
		catch( IOException e )
		{
			e.printStackTrace();
		}


		//		String htmlWithJava13 = """
		//              <html>
		//                  <body>
		//                      <p>Hello, world</p>
		//                  </body>
		//              </html>
		//              """;
		//
		//		System.out.println( htmlWithJava13 );
		//
		//		String day = "";
		//
		//		int numLetters = switch (day) {
		//			case "MONDAY", "FRIDAY", "SUNDAY" -> 6;
		//			case "TUESDAY"                -> 7;
		//			default -> throw new IllegalArgumentException("something is murky!");
		//			}
		//		};


	}

	//record AnyClassName( int x , String y){} //14 feature
}


