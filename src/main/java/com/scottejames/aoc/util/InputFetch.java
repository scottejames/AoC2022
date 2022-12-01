package com.scottejames.aoc.util;

import com.google.common.net.HttpHeaders;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;



public class InputFetch {
    private static final Logger LOG = LogManager.getLogger(InputFetch.class);

    private String _sessionToken;
    private Path _puzzleCacheDir = Path.of("puzzleData");
    private final OkHttpClient _httpClient = new OkHttpClient();

    public static void main(String [] args) throws IOException {
        InputFetch i = new InputFetch();
        String data = i.fetchPuzzleInput(1);
//        System.out.println(data);
    }
    public String fetchPuzzleInput(int day) throws IOException{
        String data = null;
        try {
             data =  fetchPuzzleInputLocally(day);
            LOG.warn("Retrieved data from local store");
        } catch (IOException e){
            LOG.warn("Could not get data for day {} locally", day);
        }
        if (data == null) {
            try {
                data = fetchPuzzleInputRemote(day);
                LOG.warn("Retrieved data from remote store");
                storePuzzleInputLocaly(day, data);
            } catch (IOException e) {
                LOG.warn("Could not get (or store!) data for day remotely", day, e);
            }
        }
        return data;
    }
    public void storePuzzleInputLocaly(int day, String input) throws IOException {
        Files.createDirectories(_puzzleCacheDir);
        var cache = _puzzleCacheDir.resolve(String.valueOf(day));
        Files.writeString(cache,input);
    }
    public String fetchPuzzleInputLocally(int day) throws IOException {
        return Files.readString(_puzzleCacheDir.resolve(String.valueOf(day)));

    }
    public String fetchPuzzleInputRemote(int day) throws IOException {
        var request = new Request.Builder()
                .url(getRemotePuzzleInputUrl(day))
                .header(HttpHeaders.COOKIE, "session=" + getSessionToken())
                .get()
                .build();

        try (var response = _httpClient.newCall(request).execute()) {
            if (response.code() != 200) {
                throw new IOException("Request was not successful. Status code = " + response.code());
            }
            var body = response.body();
            if (body == null) {
                throw new IOException("Request body was empty");
            }
            return body.string();
        }
    }

    HttpUrl getRemotePuzzleInputUrl(int day) {
        LOG.info("Getting info for {} ", day);
        return HttpUrl.get("https://adventofcode.com/2022/day/" + day + "/input");
    }
    synchronized String  getSessionToken() throws IOException {
        try {
            if (_sessionToken == null) {
                _sessionToken = Files.readString(Path.of("src/main/cookie.txt")).trim();
            }
            return _sessionToken;
        } catch (IOException e) {
            throw new IOException("Couldn't get session data from cookie.txt", e);
        }
    }
}
