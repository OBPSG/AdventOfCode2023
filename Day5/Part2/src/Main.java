import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    record MapEntry(long sourceStart, long destinationStart, long range){}
    record SeedMapEntry(long start, long range){}

    public static void main(String[] args) {
            Path path = Paths.get("input.txt");
        try {
            long result = Long.MAX_VALUE;
            List<String> inputLines = Files.readAllLines(path);
            List<SeedMapEntry> seeds = new ArrayList<>();
            Pattern numberPattern = Pattern.compile("\\d+");
            Matcher numberMatcher = numberPattern.matcher(inputLines.get(0));
            while(numberMatcher.find())
            {
                long start = Long.valueOf(numberMatcher.group());
                numberMatcher.find();
                long range = Long.valueOf(numberMatcher.group());
                seeds.add(new SeedMapEntry(start, range));
            }
            System.out.println("Seeds: " + seeds);

            int seedSoilLineNum = inputLines.indexOf("seed-to-soil map:");
            int soilFertilizerLineNum = inputLines.indexOf("soil-to-fertilizer map:");
            List<MapEntry> seedSoilMap = new ArrayList<>();
            for(int i = seedSoilLineNum + 1; i < soilFertilizerLineNum - 1; i++){
                String line = inputLines.get(i);
                long destinationStart, sourceStart, range;
                numberMatcher = numberPattern.matcher(line);
                numberMatcher.find();
                destinationStart = Long.valueOf(numberMatcher.group());
                numberMatcher.find();
                sourceStart = Long.valueOf(numberMatcher.group());
                numberMatcher.find();
                range = Long.valueOf(numberMatcher.group());
                seedSoilMap.add(new MapEntry(sourceStart, destinationStart, range));
            }

            int fertilizerWaterLineNum = inputLines.indexOf("fertilizer-to-water map:");
            List<MapEntry> soilFertilizerMap = new ArrayList<>();
            for(int i = soilFertilizerLineNum + 1; i < fertilizerWaterLineNum - 1; i++){
                String line = inputLines.get(i);
                long destinationStart, sourceStart, range;
                numberMatcher = numberPattern.matcher(line);
                numberMatcher.find();
                destinationStart = Long.valueOf(numberMatcher.group());
                numberMatcher.find();
                sourceStart = Long.valueOf(numberMatcher.group());
                numberMatcher.find();
                range = Long.valueOf(numberMatcher.group());
                soilFertilizerMap.add(new MapEntry(sourceStart, destinationStart, range));
            }
            //System.out.println(soilFertilizerMap);

            int waterLightNum = inputLines.indexOf("water-to-light map:");
            List<MapEntry> fertilizerWaterMap = new ArrayList<>();
            for(int i = fertilizerWaterLineNum + 1; i < waterLightNum - 1; i++){
                String line = inputLines.get(i);
                long destinationStart, sourceStart, range;
                numberMatcher = numberPattern.matcher(line);
                numberMatcher.find();
                destinationStart = Long.valueOf(numberMatcher.group());
                numberMatcher.find();
                sourceStart = Long.valueOf(numberMatcher.group());
                numberMatcher.find();
                range = Long.valueOf(numberMatcher.group());
                fertilizerWaterMap.add(new MapEntry(sourceStart, destinationStart, range));
            }
            //System.out.println(fertilizerWaterMap);

            int lightTemperatureLineNum = inputLines.indexOf("light-to-temperature map:");
            List<MapEntry> waterLightMap = new ArrayList<>();
            for(int i = waterLightNum + 1; i < lightTemperatureLineNum - 1; i++){
                String line = inputLines.get(i);
                long destinationStart, sourceStart, range;
                numberMatcher = numberPattern.matcher(line);
                numberMatcher.find();
                destinationStart = Long.valueOf(numberMatcher.group());
                numberMatcher.find();
                sourceStart = Long.valueOf(numberMatcher.group());
                numberMatcher.find();
                range = Long.valueOf(numberMatcher.group());
                waterLightMap.add(new MapEntry(sourceStart, destinationStart, range));
            }
            //System.out.println(waterLightMap);

            int temperatureHumidityLineNum = inputLines.indexOf("temperature-to-humidity map:");
            List<MapEntry> lightTemperatureMap = new ArrayList<>();
            for(int i = lightTemperatureLineNum + 1; i < temperatureHumidityLineNum - 1; i++){
                String line = inputLines.get(i);
                long destinationStart, sourceStart, range;
                numberMatcher = numberPattern.matcher(line);
                numberMatcher.find();
                destinationStart = Long.valueOf(numberMatcher.group());
                numberMatcher.find();
                sourceStart = Long.valueOf(numberMatcher.group());
                numberMatcher.find();
                range = Long.valueOf(numberMatcher.group());
                lightTemperatureMap.add(new MapEntry(sourceStart, destinationStart, range));
            }

            int humidityLocationLineNum = inputLines.indexOf("humidity-to-location map:");
            List<MapEntry> temperatureHumidityMap = new ArrayList<>();
            for(int i = temperatureHumidityLineNum+ 1; i < humidityLocationLineNum - 1; i++){
                String line = inputLines.get(i);
                long destinationStart, sourceStart, range;
                numberMatcher = numberPattern.matcher(line);
                numberMatcher.find();
                destinationStart = Long.valueOf(numberMatcher.group());
                numberMatcher.find();
                sourceStart = Long.valueOf(numberMatcher.group());
                numberMatcher.find();
                range = Long.valueOf(numberMatcher.group());
                temperatureHumidityMap.add(new MapEntry(sourceStart, destinationStart, range));
            }

            List<MapEntry> humidityLocationMap = new ArrayList<>();
            for(int i = humidityLocationLineNum + 1; i < inputLines.size(); i++){
                String line = inputLines.get(i);
                long destinationStart, sourceStart, range;
                numberMatcher = numberPattern.matcher(line);
                numberMatcher.find();
                destinationStart = Long.valueOf(numberMatcher.group());
                numberMatcher.find();
                sourceStart = Long.valueOf(numberMatcher.group());
                numberMatcher.find();
                range = Long.valueOf(numberMatcher.group());
                humidityLocationMap.add(new MapEntry(sourceStart, destinationStart, range));
            }

            for (SeedMapEntry entry: seeds){
                for(long i = entry.start; i < entry.start + entry.range; i++)
                {
                    long seed = i;
                    //System.out.print("Seed: " + seed + " -> ");
                    long soil = mapLookup(seedSoilMap, seed);
                    //System.out.print("Soil: " + soil + " -> ");
                    long fertilizer = mapLookup(soilFertilizerMap, soil);
                    //System.out.print("Fertilizer: " + fertilizer + " -> ");
                    long water = mapLookup(fertilizerWaterMap, fertilizer);
                    //System.out.print("Water: " + water + " -> ");
                    long light = mapLookup(waterLightMap, water);
                    //System.out.print("Light: " + light + " -> ");
                    long temperature = mapLookup(lightTemperatureMap, light);
                    //System.out.print("Temperature: " + temperature + " -> ");
                    long humidity = mapLookup(temperatureHumidityMap, temperature);
                    //System.out.print("Humidity: " + humidity + " -> ");
                    long location = mapLookup(humidityLocationMap, humidity);
                    //System.out.print("Location: " + location);
                    if(location < result)
                    {
                        result = location;
                        System.out.println(" | New Lowest Location: " + result);
                    }
                }
            }

        System.out.println("FINAL LOCATION VALUE: " + result);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static long mapLookup (List<MapEntry> map, long input){
        for(MapEntry entry : map) {
            if ((input >= entry.sourceStart) && (input <= entry.sourceStart + entry.range))
            {
                long offset = entry.destinationStart - entry.sourceStart;
                return input + offset;
            }
        }
        return input;
    }
}