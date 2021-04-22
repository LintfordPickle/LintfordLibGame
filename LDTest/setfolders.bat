@echo off

mkdir docs

mkdir libs

mkdir licenses

mkdir raw

mkdir res
mkdir res\audio
echo. 2>audio\_meta.json
mkdir res\cursors
mkdir res\fonts
echo. 2>fonts\_meta.json
mkdir res\pobjects
echo. 2>pobjects\_meta.json
mkdir res\shaders
mkdir res\spritegraphs
echo. 2>spritegraphs\_meta.json
mkdir res\spritesheets
echo. 2>spritesheets\_meta.json
mkdir res\textures
echo. 2>textures\_meta.json

:endparse