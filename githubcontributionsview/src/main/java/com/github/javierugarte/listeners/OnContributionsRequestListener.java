package com.github.javierugarte.listeners;

import com.android.volley.VolleyError;
import com.github.javierugarte.ContributionsDay;

import java.util.List;

/**
 * Copyright 2016 Javier González
 * All right reserved.
 */
public interface OnContributionsRequestListener {

    void onResponse(List<ContributionsDay> contributionsDay);

    void onError(VolleyError error);

}
