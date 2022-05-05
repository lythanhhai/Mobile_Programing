package com.midterm.myapp

sealed class Navigationitem( var route: String, var icon:Int,var title: String)
{
    object Home:Navigationitem("home",R.drawable.ic_baseline_home_24,"Home")
    object Music:Navigationitem("music",R.drawable.ic_baseline_music_note_24,"Music")
    object Movies:Navigationitem("movie",R.drawable.ic_baseline_movie_24,"Movie")
    object Book:Navigationitem("bill",R.drawable.ic_baseline_menu_book_24,"Bill")
    object Profile:Navigationitem("profile",R.drawable.ic_baseline_person_24,"Profile")
}
