package com.example.cachecleaner;

import android.content.Context;
import android.os.Bundle;
import android.os.StatFs;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button clearCacheButton = findViewById(R.id.clear_cache_button);
        clearCacheButton.setOnClickListener(v -> {
            clearApplicationCache();
            Toast.makeText(this, "Cache cleared!", Toast.LENGTH_SHORT).show();
        });
    }

    private void clearApplicationCache() {
        File cacheDir = getCacheDir();
        deleteDir(cacheDir);
    }

    private boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (String child : children) {
                boolean success = deleteDir(new File(dir, child));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }
}
