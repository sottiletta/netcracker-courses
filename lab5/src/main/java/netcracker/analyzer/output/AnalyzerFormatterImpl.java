package netcracker.analyzer.output;

import netcracker.analyzer.AnalyzerResult;
import netcracker.sorters.HalfDivisionSort;
import netcracker.sorters.Sorter;

import java.util.List;
import java.util.Map;

/**
 * {@link AnalyzerFormatter} implementation
 *
 * @author Zakh
 * @see AnalyzerResult
 */
public class AnalyzerFormatterImpl implements AnalyzerFormatter {

    /**
     * Formats output
     *
     * @param resultList A list of {@link AnalyzerResult}
     * @return String output
     */
    @Override
    public String format(List<AnalyzerResult> resultList) {
        if(resultList == null || resultList.isEmpty()) return "";
        StringBuilder resultBuilder = new StringBuilder();
        String lastSorterName = "";
        for(AnalyzerResult result : resultList){
            String sorterName;
            if(result.getSorter().getClass() == HalfDivisionSort.class){
                sorterName = result.getSorter().toString();
            }
            else sorterName = result.getSorter().getClass().getAnnotation(Sorter.class).name();

            if(!lastSorterName.equals(sorterName)){
                resultBuilder
                        .append("--------------------------\nTesting: ")
                        .append(sorterName)
                        .append("\n\n");
            }
            else{
                resultBuilder.append("\n");
            }

            resultBuilder
                    .append("Filler: ")
                    .append(result.getFiller())
                    .append("\nElapsed time: ");

            Map<Integer, Long> elapsedTime = result.getElapsedTime();

            for(Map.Entry<Integer, Long> entry : elapsedTime.entrySet()){
                resultBuilder
                        .append("\nArray size: ")
                        .append(entry.getKey())
                        .append(" | Time: ")
                        .append(entry.getValue())
                        .append(" ms.");
            }

            resultBuilder.append("\n");

            lastSorterName = sorterName;

        }
        return resultBuilder.toString();
    }

}
