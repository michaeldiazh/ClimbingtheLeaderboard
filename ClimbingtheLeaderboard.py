#!/usr/bin/python

import math
import os
import random
import re
import sys

def makePlacementList(placementList,scoreList):
    for i in range(1,len(scoreList)+1):
        tup = (scoreList[i-1],len(scoreList)-i+1)
        placementList.append(tup)
    
def makeAnsList(aliceList,placementList):
    index = 0 
    ans_list = list()
    for a_score in aliceList:
        while(index != len(placementList) and a_score >= placementList[index][0]):
            index +=1
        if index == len(placementList):
            ans_list.append(1)
            continue

        placement = placementList[index][1]+1
        ans_list.append(placement)
    return ans_list

def climbingLeaderboard(scores, alice):
    # Get Rid of duplicates (else use stack)
    score_set = set(scores)
    score_list = list(score_set)
    score_list.sort()
    placement_list = list()
    
    makePlacementList(placement_list,score_list)
    return makeAnsList(alice,placement_list)


def main():
    score = list()
    alice = list()
    file_pointer = open("input.txt",'r')
    
    # For the scoreboard
    length_of_scores = int(file_pointer.readline())
    scores_string = file_pointer.readline()
    score_list = scores_string.split(" ")
    for string_score in score_list:
        score.append(int(string_score))
    
    # For alice
    length_of_alice = int(file_pointer.readline())
    alice_string = file_pointer.readline()
    alice_list = alice_string.split(" ")
    for alice_score in alice_list:
        alice.append(int(alice_score))
    
    file_pointer.close()

    ans_list = climbingLeaderboard(score,alice)
    for i in ans_list:
        print(i)





main()