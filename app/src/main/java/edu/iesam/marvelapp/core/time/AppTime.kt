package edu.iesam.marvelapp.core.time


interface AppTime{
    fun currentTimeToLong(): Long
}

class LocalAppTime : AppTime
{
    override fun currentTimeLong(): Long {
        return System.currentTimeMillis()
    }
}