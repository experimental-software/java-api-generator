#!/usr/bin/env python3

import argparse
import os
import json

if __name__ == "__main__":
  parser = argparse.ArgumentParser()
  parser.add_argument("--version", type=str, required=True)
  parser.add_argument("--docs_base_url", type=str, required=True)
  parser.add_argument("--src", type=str, required=True)
  args = parser.parse_args()

  os.chdir(args.src)
  for root, dirs, files in os.walk("."):
    for filename in files:
      path = os.path.join(root, filename)
      if not path.endswith(".json"):
        continue

      json_file_path = path
      java_file_path = json_file_path.replace(".json", ".java")

      json_file = open(json_file_path, "r")
      ir = json.load(json_file)
      json_file.close()

      java_file = open(java_file_path, "r")
      lines_original_java_code = java_file.readlines()
      java_file.close()

      lines_updated_java_code = []
      javadocs = f"""/**
 * @see <a href="{args.docs_base_url}{ir["documentation_anchor"]}">specifications.openehr.org</a>
 */
"""
      for i in range(len(lines_original_java_code)):
        line = lines_original_java_code[i]
        if "{" in line:
          lines_updated_java_code.append(javadocs)
        lines_updated_java_code.append(line)

      java_file = open(java_file_path, "w")
      java_file.writelines(lines_updated_java_code)
      java_file.close()
        
