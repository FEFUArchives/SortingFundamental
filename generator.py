import random
import csv
import logging
import re
import os
import requests

logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s')

FIO_PATTERN = re.compile(r"^([-А-ЯЁа-яё]+) ([-А-ЯЁа-яё]+) ([-А-ЯЁа-яё]+)$")

def generate_group():
    group_type = random.choice(['Б', 'М', 'С'])
    group_number = random.randint(0, 9999)  # Allow numbers to start with zero
    return f"{group_type}{group_number:04}"  # Ensure zero-padding to 4 digits

def format_fio(fio):
    return " ".join(part.capitalize() for part in fio.split())

def read_fio_from_csv(file_path):
    fio_list = []
    with open(file_path, 'r', encoding='utf-8') as csv_file:
        reader = csv.reader(csv_file)
        for row in reader:
            if len(row) >= 3:  # Ensure there are at least 3 columns
                fio = f"{row[0]} {row[1]} {row[2]}"
                formatted_fio = format_fio(fio)
                # Include only FIO matching the regex pattern
                if FIO_PATTERN.match(formatted_fio):
                    fio_list.append(formatted_fio)
    logging.info(f"Read {len(fio_list)} valid FIO entries from the file.")
    return fio_list

def generate_groups_and_assign_fio(fio_list, total_groups=1000000, duplicate_percentage=10):
    logging.info("Starting group and FIO generation...")
    # Calculate unique and duplicate group counts
    unique_groups_count = min(30000, total_groups * (100 - duplicate_percentage) // 100)
    duplicate_groups_count = total_groups - unique_groups_count

    logging.info(f"Unique groups to generate: {unique_groups_count}")
    logging.info(f"Duplicate groups to add: {duplicate_groups_count}")

    # Generate unique groups
    unique_groups = set()
    max_possible_groups = 30000  # Total possible unique groups for the format

    while len(unique_groups) < unique_groups_count:
        unique_groups.add(generate_group())
        if len(unique_groups) % 10000 == 0:
            logging.info(f"Generated {len(unique_groups)} unique groups so far...")
        # Stop if all possible unique groups are generated
        if len(unique_groups) >= max_possible_groups:
            logging.warning("All possible unique groups have been generated.")
            break

    # Add duplicates
    duplicate_groups = random.choices(list(unique_groups), k=duplicate_groups_count)
    all_groups = list(unique_groups) + duplicate_groups

    # Shuffle all groups
    random.shuffle(all_groups)
    logging.info("Groups shuffled.")

    # Assign FIO to groups
    unique_fio_count = len(fio_list) * (100 - duplicate_percentage) // 100
    unique_fio = random.sample(fio_list, unique_fio_count)
    duplicate_fio = random.choices(unique_fio, k=total_groups - unique_fio_count)
    all_fio = unique_fio + duplicate_fio

    # Shuffle FIO list
    random.shuffle(all_fio)
    logging.info("FIO list shuffled.")

    return list(zip(all_groups, all_fio))

def save_to_csv(data, output_file):
    logging.info(f"Saving data to {output_file}...")
    with open(output_file, 'w', encoding='utf-8', newline='') as csv_file:
        writer = csv.writer(csv_file)
        writer.writerows(data)
    logging.info(f"Data saved to {output_file}.")

def download_file_if_missing(file_path, url):
    if not os.path.exists(file_path):
        logging.info(f"File {file_path} not found. Downloading from {url}...")
        response = requests.get(url)
        if response.status_code == 200:
            with open(file_path, 'wb') as file:
                file.write(response.content)
            logging.info(f"File {file_path} downloaded successfully.")
        else:
            logging.error(f"Failed to download file from {url}. Status code: {response.status_code}")
            raise Exception(f"Unable to download file: {url}")

if __name__ == "__main__":
    input_csv = "data.csv"
    output_csv = "input.csv"
    data_url = "https://acdn.edwardcode.net/edu/fsdia/fios.csv"

    download_file_if_missing(input_csv, data_url)
    fio_list = read_fio_from_csv(input_csv)

    result = generate_groups_and_assign_fio(fio_list)
    save_to_csv(result, output_csv)

    logging.info(f"Generated data saved to {output_csv}")
