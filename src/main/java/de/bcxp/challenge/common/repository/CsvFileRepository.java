package de.bcxp.challenge.common.repository;
 import de.bcxp.challenge.common.exception.DataReadingException;
 import de.bcxp.challenge.common.mapper.LineMapper;

 import java.io.IOException;
 import java.nio.file.Files;
 import java.nio.file.Path;
 import java.util.List;
 import java.util.Objects;
 import java.util.stream.Collectors;
 import java.util.stream.Stream;

public class CsvFileRepository<T> {
    private final Path filePath;
    private final LineMapper<T> mapper;

    public CsvFileRepository(Path filePath, LineMapper<T> mapper) {
        this.filePath = filePath;
        this.mapper = mapper;
    }

    public List<T> loadAll() {
        try (Stream<String> lines = Files.lines(filePath)) {
            return lines
                    .skip(1)
                    .filter(line -> !line.isBlank())
                    .map(line -> {
                        try {
                            return mapper.map(line);
                        } catch (Exception e) {
                            return null;
                        }
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new DataReadingException("Fehler beim Lesen: " + filePath, e);
        }
    }
}