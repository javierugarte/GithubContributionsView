package com.github.javierugarte.listeners;

import android.graphics.Bitmap;

import com.github.javierugarte.GitHubContributionsView;

/**
 * Copyright 2016 Javier González
 * All right reserved.
 */
public interface OnContributionsListener {

    void onResponse(GitHubContributionsView view, Bitmap bitmap);

    void onError(int cause);

}
