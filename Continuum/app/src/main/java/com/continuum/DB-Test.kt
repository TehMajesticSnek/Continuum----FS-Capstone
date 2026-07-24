package com.continuum
import android.content.ContentValues.TAG
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns
import android.util.Log

import kotlinx.serialization.Serializable

@Serializable
data class UserRow(
    val Name: String // This MUST match the exact capitalization of your column in Supabase!
)
val supabase = createSupabaseClient(
    supabaseUrl = "https://nkpxjjlvzbfzcpcxbraf.supabase.co",
    supabaseKey = "sb_publishable_RoUXBvS61E45Lzkiw-7JmA_bKjYHX4F" // Public key. Safe to hard code I'm pretty sure. Make sure RLS is on in all tables though
) {
    install(Postgrest)
}
class Database {

    suspend fun testDB() : String {
        val result = supabase.from("test").select(columns = Columns.list("Name")).decodeSingle<UserRow>()
        Log.d(TAG, result.Name)
        return result.Name
    }
}
