package com.example.planup.repository

import android.util.Log
import com.example.planup.model.TaskList
import com.example.planup.model.TaskListRequest
import com.example.planup.model.TaskListUpdateRequest
import com.example.planup.network.RetrofitInstance
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TaskListRepository {
    private val apiService = RetrofitInstance.apiService

    fun deleteList(projectId: String, listId: String, callback: (Boolean) -> Unit) {
        apiService.deleteList(projectId, listId).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    Log.d("DeleteList", "Lista deletada com sucesso: ${response.body()}")
                    callback(true)
                } else {
                    Log.d(
                        "DeleteList",
                        "Falha ao deletar a lista: ${response.errorBody()?.string()}"
                    )
                    callback(false)
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.e("DeleteList", "Erro ao deletar a lista: ${t.message}")
                t.printStackTrace()
                callback(false)
            }
        })
    }

    fun postProjectList(list: TaskListRequest) {
        apiService.postList(list).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {

                if (response.isSuccessful) {
                    Log.d("PostList", "Lista criada com sucesso: ${response.body()}")
                } else {
                    Log.e("PostList", "Falha ao criar lista: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.e("PostList", "Erro ao enviar a lista: ${t.message}")
                t.printStackTrace()
            }
        })
    }

    fun updateTaskList(taskListUpdateRequest: TaskListUpdateRequest, callback: (Boolean) -> Unit) {
        apiService.updateTaskList(taskListUpdateRequest).enqueue(object : Callback<ResponseBody>{
            override fun onResponse(p0: Call<ResponseBody>, p1: Response<ResponseBody>) {
                if(p1.isSuccessful){
                    callback(true)
                    Log.d("UpdateTaskList", "Lista atualizada com sucesso: ${p1.body()}")
                } else {
                    callback(false)
                    Log.d("UpdateTaskList", "Falha ao atualizar lista: ${p1.errorBody()?.string()}")
                }
            }

            override fun onFailure(p0: Call<ResponseBody>, p1: Throwable) {
                callback(false)
                Log.e("UpdateTaskList", "Erro ao atualizar lista: ${p1.message}")
            }
        })
    }
}
