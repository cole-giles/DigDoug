package custommap;

import world.TileType;

import java.util.Random;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

import static com.badlogic.gdx.net.HttpRequestBuilder.json;

public class CustomMapLoader {

    private static final int SIZE = 100;

    public static CustomMapData loadMap(String id, String name){
        Gdx.files.local("maps/").file().mkdirs();
        FileHandle file = Gdx.files.local("maps/" + id + ".map");
        if (file.exists()){
           CustomMapData data = json.fromJson(CustomMapData.class, file.readString());
           return data;
        } else {
            CustomMapData data = generateRandomMap(id, name);
            return data;
        }
    }

    public static CustomMapData generateRandomMap (String id, String name) {
        CustomMapData mapData = new CustomMapData();
        mapData.id = id;
        mapData.name = id;
        mapData.map = new int[2][SIZE][SIZE];

        Random random = new Random();

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                mapData.map[0][row][col] = TileType.SKY.getId();

                if (row > SIZE - 3 - 1) {
                    mapData.map[1][row][col] = TileType.LAVA.getId();
                } else if (row > SIZE - 20) {
                    mapData.map[1][row][col] = TileType.STONE.getId();
                } else if (row > SIZE - 25) {
                    mapData.map[1][row][col] = TileType.DIRT.getId();
                } else if (row > SIZE - 26) {
                    mapData.map[1][row][col] = TileType.GRASS.getId();
                } else {
                    if (random.nextInt(50) == 0)//1 chance out of 50 of being a cloud
                        mapData.map[1][row][col] = TileType.CLOUD.getId();
                }
            }
        }

        return mapData;
    }

}
