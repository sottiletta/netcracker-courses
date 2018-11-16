package netcracker.analyzer.output;

import netcracker.analyzer.AnalyzerResult;
import netcracker.fillers.Option;
import netcracker.sorters.HalfDivisionSort;

import java.util.List;

public class AnalyzerFormatterImpl implements AnalyzerFormatter {

    @Override
    public String format(List<AnalyzerResult> resultList) {
        StringBuilder resultBuilder = new StringBuilder();
        String lastSorterName = "";
        for(AnalyzerResult result : resultList){
            String sorterName = result.getSorter().toString();

            if(!lastSorterName.equals(sorterName)){
                resultBuilder.append("--------------------------\nTesting: ");
                resultBuilder.append(sorterName);
                resultBuilder.append("\n\n");
            }
            else{
                resultBuilder.append("\n");
            }

            resultBuilder.append("Filler: ");
            resultBuilder.append(result.getFiller());

            resultBuilder.append("\nElapsed time: ");
            resultBuilder.append(result.getElapsedTime());
            resultBuilder.append(" ms.\n");
            lastSorterName = sorterName;

        }
        return resultBuilder.toString();
    }

}
