/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.RizqiNafianDiraga

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView

class MovieAdapter(private val letterId: String, context: Context) :
    RecyclerView.Adapter<MovieAdapter.WordViewHolder>() {

    // menyiapakan variable untuk filter
    private val filteredWords: List<String>

    init {
        // Mengambil list array dari res/value/array
        val words = context.resources.getStringArray(R.array.Film).toList()

        filteredWords = words
            // menampilkan data yang tersaing (yang terdapat kata itu dan menghirukan besar kecilnya)
            .filter { it.contains(letterId, ignoreCase = true) }
            // mengacak data
            .shuffled()
            // mengurutkan data yang di tampilkan
            .sorted()
    }

    class WordViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val button = view.findViewById<Button>(R.id.button_item)
    }

    override fun getItemCount(): Int = filteredWords.size

    // membuat R.layout.item_view masuk ke layout parant
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_view, parent, false)

        // mengtur aksesdelegasi mejadi Accessibility (text dapat terbaca)
        layout.accessibilityDelegate = Accessibility

        return WordViewHolder(layout)
    }

    // mengganti isi konten
    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {

        // memangil data yang telah di filter
        val item = filteredWords[position]
        // Needed to call startActivity
        val context = holder.view.context

        // mengatur text kedalam view
        holder.button.text = item
        holder.button.setOnClickListener {
            val queryUrl: Uri = Uri.parse("${MovieListFragment.SEARCH_PREFIX}${item}")
            val intent = Intent(Intent.ACTION_VIEW, queryUrl)
            context.startActivity(intent)
        }
    }
    // Setup custom accessibility delegate to set the text read with
    // an accessibility service
    companion object Accessibility : View.AccessibilityDelegate() {
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun onInitializeAccessibilityNodeInfo(
            host: View,
            info: AccessibilityNodeInfo
        ) {
            super.onInitializeAccessibilityNodeInfo(host, info)
            // With `null` as the second argument to [AccessibilityAction], the
            // accessibility service announces "double tap to activate".
            // If a custom string is provided,
            // it announces "double tap to <custom string>".
            val customString = host.context?.getString(R.string.look_up_word)
            val customClick =
                AccessibilityNodeInfo.AccessibilityAction(
                    AccessibilityNodeInfo.ACTION_CLICK,
                    customString
                )
            info.addAction(customClick)
        }
    }
}