#!/bin/bash

set -e

OUTPUT_FILE="BookStore-Backend.zip"

echo "开始打包项目到 $OUTPUT_FILE ..."

# 使用 zip 命令进行压缩
# -r: 递归处理目录
# -q: 安静模式，减少不必要的输出
# .:  代表当前目录
# -x: 排除指定的文件或目录
zip -rq "$OUTPUT_FILE" . \
    -x ".git/*" \
    -x "target/*" \
    -x ".vscode/*" \
    -x "pack.sh" \
    -x "$OUTPUT_FILE"

echo "文件已保存为: $OUTPUT_FILE"