package br.com.fiap.pokeagenda.api

import br.com.fiap.pokeagenda.Model.Pkmn
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * Created by logonrm on 21/02/2018.
 */
interface PkmnApi{
    @GET("/api/v2/pokemon/{numero}")
    fun buscarPkmn(@Path("numero") numeroPkmn: Int): Call<Pkmn>
}